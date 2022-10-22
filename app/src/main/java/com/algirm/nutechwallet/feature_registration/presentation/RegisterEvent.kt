package com.algirm.nutechwallet.feature_registration.presentation

sealed class RegisterEvent {
    data class FirstNameChanged(val firstName: String) : RegisterEvent()
    data class LastNameChanged(val lastName: String) : RegisterEvent()
    data class EmailChanged(val emailAddress: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    object Register: RegisterEvent()
}