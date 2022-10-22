package com.algirm.nutechwallet.feature_ewallet.data.remote.dto

import com.algirm.nutechwallet.core.domain.model.ApiResponse
import com.google.gson.annotations.SerializedName

data class BalanceDto(
    override val status: Int,
    override val message: String,
    override val data: Balance?
) : ApiResponse
