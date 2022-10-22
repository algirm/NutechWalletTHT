package com.algirm.nutechwallet.feature_ewallet.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.Transaction
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getTransactions(): List<Transaction>

    @Insert
    suspend fun addTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}