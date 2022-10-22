package com.algirm.nutechwallet.feature_ewallet.data.repository

import android.util.Log
import com.algirm.nutechwallet.core.util.AppConst.Companion.TAG
import com.algirm.nutechwallet.feature_ewallet.data.local.EwalletDatabase
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.data.remote.EwalletApi
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionDto
import com.algirm.nutechwallet.feature_ewallet.domain.model.TokenExpiredException
import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository

class EwalletRepositoryImpl(
    private val ewalletApi: EwalletApi,
    private val userDb: EwalletDatabase
) : EwalletRepository {

    override suspend fun getBalance(): Result<Int> {
        return try {
            val result = ewalletApi.getBalance()
            if (result.isSuccessful) {
                Log.d(TAG, "getBalance: $result")
                Log.d(TAG, "getBalance: ${result.body()}")
                val code = result.body()!!.status
                if (code == 0) {
                    val balance: Int = result.body()?.data?.balance?.toInt() ?: 0
                    Result.success(balance)
                } else {
                    Result.failure(TokenExpiredException())
                }
            } else {
                Log.d(TAG, "EwalletRepositoryImpl: $result")
                Result.failure(Exception(result.message()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "EwalletRepositoryImpl: ", e)
            Result.failure(e)
        }
    }

    override suspend fun topUp(amount: Int): Result<String> {
        return try {
            val mapRequest = mapOf("amount" to amount)
            val result = ewalletApi.topUp(mapRequest)

            if (result.isSuccessful) {
                Log.d(TAG, "topUp: $result")
                Log.d(TAG, "topUp: ${result.body()}")

                val code = result.body()!!.status

                if (code == 0) {
                    val message = result.body()?.message
                    Result.success(message?: "")
                } else {
                    Result.failure(TokenExpiredException())
                }
            } else {
                Log.d(TAG, "EwalletRepositoryImpl: $result")
                Result.failure(Exception(result.message()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "EwalletRepositoryImpl: ", e)
            Result.failure(e)
        }
    }

    override suspend fun transfer(amount: Int): Result<String> {
        return try {
            val result = ewalletApi.transfer(mapOf("amount" to amount))

            if (result.isSuccessful) {
                Log.d(TAG, "transfer: $result")
                Log.d(TAG, "transfer: ${result.body()}")

                val code = result.body()!!.status
                if (code == 0) {
                    val message = result.body()?.message
                    Result.success(message?: "")
                } else {
                    Result.failure(TokenExpiredException())
                }
            } else {
                Log.d(TAG, "EwalletRepositoryImpl: $result")
                Result.failure(Exception(result.message()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "EwalletRepositoryImpl: ", e)
            Result.failure(e)
        }
    }

    override suspend fun getTransactionHistory(): Result<List<TransactionDto>> {
        return try {
            val result = ewalletApi.getTransactionHistory()

            if (result.isSuccessful) {
                Log.d(TAG, "getTransactionHistory: $result")
                Log.d(TAG, "getTransactionHistory: ${result.body()}")
                val code = result.body()!!.status
                if (code == 0) {
                    Result.success(result.body()?.data!!)
                } else {
                    Result.failure(TokenExpiredException())
                }
            } else {
                Log.d(TAG, "EwalletRepositoryImpl: $result")
                Result.failure(Exception(result.message()))
            }
        } catch (e: Exception) {
            Log.e(TAG, "EwalletRepositoryImpl: ", e)
            Result.failure(e)
        }
    }
}