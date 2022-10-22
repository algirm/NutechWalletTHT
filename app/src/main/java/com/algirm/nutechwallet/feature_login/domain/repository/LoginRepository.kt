package com.algirm.nutechwallet.feature_login.domain.repository

import com.algirm.nutechwallet.feature_login.domain.model.LoginRequest
import com.algirm.nutechwallet.feature_login.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}