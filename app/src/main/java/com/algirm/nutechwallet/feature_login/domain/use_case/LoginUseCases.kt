package com.algirm.nutechwallet.feature_login.domain.use_case

data class LoginUseCases(
    val login: Login,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val saveUserInfo: SaveUserInfo
)