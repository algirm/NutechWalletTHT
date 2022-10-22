package com.algirm.nutechwallet.feature_ewallet.data.remote

import com.algirm.nutechwallet.core.domain.model.ApiResponse
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.BalanceDto
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TopUpResponse
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionHistoryDto
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransferResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface EwalletApi {

//    @Headers("Content-Type: application/json")
    @GET("/balance")
    suspend fun getBalance(): Response<BalanceDto>

//    @Headers("Content-Type: application/json")
    @POST("/topup")
    suspend fun topUp(@Body amount: Map<String, Int>): Response<TopUpResponse>

    @POST("/transfer")
    suspend fun transfer(@Body amount: Map<String, Int>): Response<TransferResponse>

    @GET("/transactionHistory")
    suspend fun getTransactionHistory(): Response<TransactionHistoryDto>
}