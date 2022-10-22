package com.algirm.nutechwallet.feature_login.data

import android.util.Log
import com.algirm.nutechwallet.core.util.AppConst.Companion.TAG
import com.algirm.nutechwallet.feature_login.domain.model.LoginRequest
import com.algirm.nutechwallet.feature_login.domain.model.LoginResponse
import com.algirm.nutechwallet.feature_login.domain.repository.LoginRepository
import retrofit2.awaitResponse

class LoginRepositoryImpl(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val loginResult = loginApi.login(loginRequest).awaitResponse()
            if (loginResult.isSuccessful) {
                Result.success(loginResult.body()!!)
            } else {
                Log.d(TAG, "loginRepositoryImpl: $loginResult")
                Result.failure(Exception(loginResult.message()))
            }
        } catch (e: Exception) {
            Log.e("DebugLogger", "loginRepositoryImpl: ", e)
            Result.failure(e)
        }
    }
}