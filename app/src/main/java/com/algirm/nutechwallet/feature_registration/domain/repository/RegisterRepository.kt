package com.algirm.nutechwallet.feature_registration.domain.repository

import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterResponse

interface RegisterRepository {

    suspend fun register(requestBody: RegisterRequest): Result<RegisterResponse>
}