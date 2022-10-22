package com.algirm.nutechwallet.feature_registration.data

import com.algirm.nutechwallet.feature_registration.domain.model.RegisterRequest
import com.algirm.nutechwallet.feature_registration.domain.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {

    @POST("/registration")
    fun register(@Body requestBody: RegisterRequest): Call<RegisterResponse>
}