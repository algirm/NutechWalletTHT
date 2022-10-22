package com.algirm.nutechwallet.feature_ewallet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val userId: Int?,
    val amount: String?,
    val date: Date?
)
