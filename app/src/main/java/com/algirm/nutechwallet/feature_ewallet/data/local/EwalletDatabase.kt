package com.algirm.nutechwallet.feature_ewallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.Transaction
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User

@Database(
    entities = [
        User::class,
        Transaction::class
    ],
    version = 1
)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class EwalletDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val transactionDao: TransactionDao
}