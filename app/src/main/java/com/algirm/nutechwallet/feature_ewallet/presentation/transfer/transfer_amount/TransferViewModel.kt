package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.transfer_amount

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core.util.UiText
import com.algirm.nutechwallet.feature_ewallet.domain.model.TokenExpiredException
import com.algirm.nutechwallet.feature_ewallet.domain.use_case.EwalletUseCases
import com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val ewalletUseCases: EwalletUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow("state", TransferState()).stateIn(
        initialValue = TransferState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.OnAmountChanged -> {
                savedStateHandle["state"] = state.value.copy(amount = event.amount)
            }
            is TransferEvent.OnTransfer -> transfer(event.amount.toInt(), event.userId)
        }
    }

    private fun transfer(amount: Int, userId: Int) {
        viewModelScope.launch {
            savedStateHandle["state"] = state.value.copy(
                isLoading = true
            )

            ewalletUseCases
                .transfer(amount, userId)
                .onSuccess {
                    _uiEvent.send(UiEvent.Success)
                }
                .onFailure {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.DynamicString(it.message?: "Something Wrong, try again.")
                        )
                    )
                    if (it is TokenExpiredException) {
                        // go to login
                    }
                }
        }
    }

}