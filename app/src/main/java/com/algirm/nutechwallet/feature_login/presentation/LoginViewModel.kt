package com.algirm.nutechwallet.feature_login.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.nutechwallet.core.domain.preferences.Preferences
import com.algirm.nutechwallet.core.util.AppConst
import com.algirm.nutechwallet.core.util.AppConst.Companion.TAG
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core.util.UiText
import com.algirm.nutechwallet.feature_login.domain.model.LoginRequest
import com.algirm.nutechwallet.feature_login.domain.use_case.LoginUseCases
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.presentation.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow("state", LoginState()).stateIn(
        initialValue = LoginState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        savedStateHandle["state"] =
            savedStateHandle.get<LoginState>("state")?.copy(isLoading = false)
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                savedStateHandle["state"] = state.value.copy(textEmail = event.emailAddress)
            }
            is LoginEvent.PasswordChanged -> {
                savedStateHandle["state"] = state.value.copy(textPassword = event.password)
            }
            is LoginEvent.Login -> {
                login(
                    LoginRequest(
                        email = state.value.textEmail,
                        password = state.value.textPassword
                    )
                )
            }
        }
    }

    private fun login(requestBody: LoginRequest) {
        viewModelScope.launch {
            savedStateHandle["state"] = state.value.copy(isLoading = true)
            loginUseCases
                .login(requestBody)
                .onSuccess { response ->
                    if (response.status == 0) {
                        val userInfo = response.data
                        Log.d(TAG, "login: $userInfo")

                        if (userInfo != null) {
                            loginUseCases.saveUserInfo(userInfo)
                            _uiEvent.send(UiEvent.Success)
                        } else {
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    UiText.DynamicString("Invalid User Data")
                                )
                            )
                        }
                    } else {
                        val message = response.message.ifBlank { "Invalid Email or Password" }
                        _uiEvent.send(UiEvent.ShowSnackbar(message = UiText.DynamicString(message)))
                    }
                    savedStateHandle["state"] = state.value.copy(isLoading = false)
                }
                .onFailure {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.DynamicString(it.message ?: "Unknown Error")
                        )
                    )
                    savedStateHandle["state"] = state.value.copy(isLoading = false)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(AppConst.TAG, "onCleared: LoginViewModel destroyed")
    }
}