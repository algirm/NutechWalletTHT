package com.algirm.nutechwallet.feature_ewallet.data.remote.dto

import android.os.Parcelable
import com.algirm.nutechwallet.core.domain.model.ApiResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionHistoryDto(
    override val status: Int,
    override val message: String,
    override val data: List<TransactionDto>?
) : ApiResponse, Parcelable
