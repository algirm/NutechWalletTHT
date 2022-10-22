package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list

import android.os.Parcelable
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserListState(
    val userList: List<User> = emptyList(),
    val isLoading: Boolean = false
) : Parcelable