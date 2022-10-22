package com.algirm.nutechwallet.feature_login.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)
