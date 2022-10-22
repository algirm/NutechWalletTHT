package com.algirm.nutechwallet.feature_ewallet.data.repository

import com.algirm.nutechwallet.feature_ewallet.data.local.EwalletDatabase
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.Transaction
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository

class LocalRepositoryImpl(
    private val ewalletDatabase: EwalletDatabase
) : LocalRepository {

    override suspend fun getUsers(): List<User> {
        return ewalletDatabase.userDao.getUsers()
    }

    override suspend fun addUser(user: User) {
        return ewalletDatabase.userDao.addUser(user)
    }

    override suspend fun deleteUser(user: User) {
        return ewalletDatabase.userDao.deleteUser(user)
    }

    override suspend fun getTransactions(): List<Transaction> {
        return ewalletDatabase.transactionDao.getTransactions()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        return ewalletDatabase.transactionDao.addTransaction(transaction)
    }
}