package com.algirm.nutechwallet.feature_login.domain.model

import com.algirm.nutechwallet.core.domain.model.ApiResponse
import com.algirm.nutechwallet.core.domain.model.UserInfo

data class LoginResponse(
    override val status: Int,
    override val message: String,
    override val data: UserInfo?
) : ApiResponse
