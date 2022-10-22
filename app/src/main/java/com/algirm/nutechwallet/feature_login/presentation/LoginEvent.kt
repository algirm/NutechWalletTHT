package com.algirm.nutechwallet.feature_login.presentation

sealed class LoginEvent {
    data class EmailChanged(val emailAddress: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Login: LoginEvent()
}