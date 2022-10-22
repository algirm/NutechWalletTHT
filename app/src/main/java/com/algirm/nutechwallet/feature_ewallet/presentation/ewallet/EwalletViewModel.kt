package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.nutechwallet.core.util.AppConst
import com.algirm.nutechwallet.core.util.AppConst.Companion.TAG
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core.util.UiText
import com.algirm.nutechwallet.feature_ewallet.domain.model.TokenExpiredException
import com.algirm.nutechwallet.feature_ewallet.domain.use_case.EwalletUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EwalletViewModel @Inject constructor(
    private val ewalletUseCases: EwalletUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow("state", EwalletState()).stateIn(
        initialValue = EwalletState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        savedStateHandle["state"] =
            savedStateHandle.get<EwalletState>("state")?.copy(isLoading = false)
        refreshBalanceInfo()
        getTransactionHistory()
    }

    fun onEvent(event: EwalletEvent) {
        when (event) {
            is EwalletEvent.TopUp -> topUp(event.amount)
        }
    }

    private fun getTransactionHistory() {
        viewModelScope.launch {
            savedStateHandle["state"] = state.value.copy(isLoading = true)

            ewalletUseCases
                .getTransactionHistory()
                .onSuccess { transactions ->
                    savedStateHandle["state"] = state.value.copy(
                        transactionHistory = transactions,
                        isLoading = false
                    )
                }
                .onFailure {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.DynamicString(it.message?: "Something Wrong, try again.")
                        )
                    )
                    savedStateHandle["state"] = state.value.copy(isLoading = false)
                    if (it is TokenExpiredException) {
                        // go to login
                    }
                }
        }
    }

    private fun topUp(amount: Int) {
        viewModelScope.launch {
            // set loading

            ewalletUseCases
                .topUp(amount)
                .onSuccess { responseMessage ->
                    Log.d(TAG, "topUp: onSuccess: $responseMessage")
                    refreshBalanceInfo()
                    getTransactionHistory()
                }
                .onFailure {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.DynamicString(it.message ?: "Unknown Error")
                        )
                    )
                    if (it is TokenExpiredException) {
                        // go to login
                    }
                }
        }
    }

    private fun refreshBalanceInfo() {
        viewModelScope.launch {
            // set loading

            ewalletUseCases
                .getBalance()
                .onSuccess { balanceAmount ->
                    savedStateHandle["state"] = state.value.copy(balance = balanceAmount)
                    Log.d(TAG, "refreshBalanceInfo onsuccess: $balanceAmount")
                }
                .onFailure {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.DynamicString(it.message ?: "Unknown Error")
                        )
                    )
                    if (it is TokenExpiredException) {
                        // go to login
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(AppConst.TAG, "onCleared: EwalletViewModel destroyed")
    }
}