package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

import android.os.Parcelable
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class EwalletState(
    val balance: Int? = null,
    val transactionHistory: List<TransactionDto> = emptyList(),
    val isLoading: Boolean = false
) : Parcelable
