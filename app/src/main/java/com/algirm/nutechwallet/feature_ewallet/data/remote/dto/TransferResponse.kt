package com.algirm.nutechwallet.feature_ewallet.data.remote.dto

import com.algirm.nutechwallet.core.domain.model.ApiResponse

data class TransferResponse(
    override val status: Int,
    override val message: String,
    override val data: Any?
) : ApiResponse
