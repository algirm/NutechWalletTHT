package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.transfer_amount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferState(
    val isLoading: Boolean = false,
    val amount: String = "10000"
) : Parcelable
