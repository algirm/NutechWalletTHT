package com.algirm.nutechwallet.feature_registration.domain.use_case

import com.algirm.nutechwallet.feature_login.domain.use_case.ValidateEmail
import com.algirm.nutechwallet.feature_login.domain.use_case.ValidatePassword

data class RegisterUseCases(
    val register: Register,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
