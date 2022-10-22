package com.algirm.nutechwallet.feature_registration.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.nutechwallet.core.util.AppConst.Companion.TAG
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core.util.UiText
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.domain.use_case.RegisterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCases: RegisterUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val textEmail = savedStateHandle.getStateFlow<String>("textEmail", "")
    private val textPassword = savedStateHandle.getStateFlow<String>("textPassword", "")
    private val isLoading = savedStateHandle.getStateFlow("isLoading", false)

    val state = savedStateHandle.getStateFlow("state", RegisterState()).stateIn(
        initialValue = RegisterState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
//        savedStateHandle["state"] = state.value.copy(isLoading = false)
        savedStateHandle["state"] = savedStateHandle.get<RegisterState>("state")?.copy(isLoading = false)
        Log.d(TAG, "initRegisterViewModel: ${savedStateHandle.get<RegisterState>("state")}")
    }

//    private val _state = MutableStateFlow(RegisterState())
//    val state = _state.stateIn(
//        initialValue = RegisterState(),
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000)
//    )

//    val state = combine(textEmail, textPassword, isLoading) { textEmail, textPassword, isLoading ->
//        RegisterState(
//            textEmail = textEmail,
//            textPassword = textPassword,
//            isLoading = isLoading
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), RegisterState())

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.FirstNameChanged -> {
                savedStateHandle["state"] = state.value.copy(textFirstName = event.firstName)
//                savedStateHandle["state"] = savedStateHandle.get<RegisterState>("state")?.copy(textFirstName = event.firstName)
            }
            is RegisterEvent.LastNameChanged -> {
                savedStateHandle["state"] = state.value.copy(textLastName = event.lastName)
            }
            is RegisterEvent.EmailChanged -> {
//                _state.update { it.copy(textEmail = event.emailAddress) }
                savedStateHandle["state"] = state.value.copy(textEmail = event.emailAddress)
            }
            is RegisterEvent.PasswordChanged -> {
                savedStateHandle["state"] = state.value.copy(textPassword = event.password)
            }
            is RegisterEvent.Register -> registerAccount(RegisterRequest(
                firstName = state.value.textFirstName,
                lastName = state.value.textLastName,
                email = state.value.textEmail,
                password = state.value.textPassword,
            ))
        }
    }

    private fun registerAccount(requestBody: RegisterRequest) {
        viewModelScope.launch {
            savedStateHandle["state"] = state.value.copy(isLoading = true)
            registerUseCases
                .register(requestBody)
                .onSuccess { response ->
                    savedStateHandle["state"] = state.value.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.DynamicString(response.message)
                        )
                    )
                    if (response.status == 0) {
                        _uiEvent.send(UiEvent.NavigateUp)
                    }
                }
                .onFailure {
                    savedStateHandle["state"] = state.value.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.DynamicString(it.message ?: "Unknown Error")
                        )
                    )
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: RegisterViewModel destroyed")
    }
}