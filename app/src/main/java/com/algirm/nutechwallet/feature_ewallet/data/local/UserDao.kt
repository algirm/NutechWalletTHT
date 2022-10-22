package com.algirm.nutechwallet.feature_ewallet.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}