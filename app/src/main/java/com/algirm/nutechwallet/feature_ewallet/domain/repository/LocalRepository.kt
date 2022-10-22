package com.algirm.nutechwallet.feature_ewallet.domain.repository

import com.algirm.nutechwallet.feature_ewallet.data.local.entity.Transaction
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User

interface LocalRepository {

    suspend fun getUsers(): List<User>

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getTransactions(): List<Transaction>

    suspend fun addTransaction(transaction: Transaction)

}