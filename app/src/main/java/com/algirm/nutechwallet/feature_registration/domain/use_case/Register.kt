package com.algirm.nutechwallet.feature_registration.domain.use_case

import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterResponse
import com.algirm.nutechwallet.feature_registration.domain.repository.RegisterRepository

class Register(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(requestBody: RegisterRequest): Result<RegisterResponse> {
        return registerRepository.register(requestBody)
    }
}