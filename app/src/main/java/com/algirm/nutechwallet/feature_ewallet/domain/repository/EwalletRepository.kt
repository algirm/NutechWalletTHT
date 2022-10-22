package com.algirm.nutechwallet.feature_ewallet.domain.repository

import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.BalanceDto
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionDto

interface EwalletRepository {

    suspend fun getBalance(): Result<Int>

    suspend fun topUp(amount: Int): Result<String>

    suspend fun transfer(amount: Int): Result<String>

    suspend fun getTransactionHistory(): Result<List<TransactionDto>>

}