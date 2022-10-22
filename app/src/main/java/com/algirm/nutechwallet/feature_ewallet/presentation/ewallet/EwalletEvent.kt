package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

sealed interface EwalletEvent {
    data class TopUp(val amount: Int) : EwalletEvent
}