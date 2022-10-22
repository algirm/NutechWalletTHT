package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.domain.use_case.EwalletUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AccountListViewModel @Inject constructor(
    private val ewalletUseCases: EwalletUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow("state", UserListState()).stateIn(
        initialValue = UserListState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    init {
//        addUser(User(name = "Person ${Random.nextInt()}"))
        getUserList()
    }

    fun onEvent(event: AccountListEvent) {
        when (event) {
            is AccountListEvent.OnAddUserClick -> {
                addUser(User(name = "Person ${Random.nextInt()}"))
            }
            is AccountListEvent.OnUserClick -> {

            }
        }
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            ewalletUseCases.addUser(user)
            getUserList()
        }
    }

    private fun getUserList() {
        viewModelScope.launch {
            savedStateHandle["state"] = state.value.copy(
                isLoading = true
            )
            val users = ewalletUseCases.getUsers()
            savedStateHandle["state"] = state.value.copy(
                userList = users,
                isLoading = false
            )
        }
    }
}