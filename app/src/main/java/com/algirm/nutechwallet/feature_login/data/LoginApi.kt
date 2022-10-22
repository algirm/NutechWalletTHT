package com.algirm.nutechwallet.feature_login.data

import com.algirm.nutechwallet.core.domain.model.UserInfo
import com.algirm.nutechwallet.feature_login.domain.model.LoginRequest
import com.algirm.nutechwallet.feature_login.domain.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    //    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body requestBody: LoginRequest): Call<LoginResponse>

}