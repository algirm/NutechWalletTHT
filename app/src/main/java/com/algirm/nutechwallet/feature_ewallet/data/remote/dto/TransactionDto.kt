package com.algirm.nutechwallet.feature_ewallet.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionDto(
    val transaction_id: String,
    val transaction_time: String,
    val transaction_type: String,
    val amount: Int
) : Parcelable