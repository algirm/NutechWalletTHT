package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list

sealed interface AccountListEvent {
    object OnAddUserClick : AccountListEvent
    data class OnUserClick(val userId: String) : AccountListEvent
}