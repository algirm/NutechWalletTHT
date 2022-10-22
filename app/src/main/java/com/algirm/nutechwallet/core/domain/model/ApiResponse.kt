package com.algirm.nutechwallet.core.domain.model

interface ApiResponse {
    val status: Int
    val message: String
    val data: Any?
}