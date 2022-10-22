package com.algirm.nutechwallet.feature_ewallet.domain.model

class TokenExpiredException : Exception() {
    override val message = "Token is Expired"
}