package com.algirm.nutechwallet.feature_login.domain.use_case

import com.algirm.nutechwallet.feature_login.domain.model.LoginRequest
import com.algirm.nutechwallet.feature_login.domain.model.LoginResponse
import com.algirm.nutechwallet.feature_login.domain.repository.LoginRepository

class Login(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(loginRequest: LoginRequest): Result<LoginResponse> {
        return loginRepository.login(loginRequest)
    }

}