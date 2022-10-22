package com.algirm.nutechwallet.feature_registration.data.repository

import com.algirm.nutechwallet.feature_registration.data.RegisterApi
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterResponse
import com.algirm.nutechwallet.feature_registration.domain.repository.RegisterRepository
import retrofit2.awaitResponse

class RegisterRepositoryImpl(
    private val registerApi: RegisterApi
) : RegisterRepository {

    override suspend fun register(requestBody: RegisterRequest): Result<RegisterResponse> {
        return try {
            val registerResult = registerApi.register(requestBody).awaitResponse()
            if (registerResult.isSuccessful) {
                Result.success(registerResult.body()!!)
            } else {
                Result.failure(Exception())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}