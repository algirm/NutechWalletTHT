package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.transfer_amount

sealed interface TransferEvent {
    data class OnAmountChanged(val amount: String) : TransferEvent
    data class OnTransfer(val amount: String, val userId: Int) : TransferEvent
}