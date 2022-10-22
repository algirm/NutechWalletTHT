package com.algirm.nutechwallet.core.domain.model

data class UserInfo(
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val token: String?
)
