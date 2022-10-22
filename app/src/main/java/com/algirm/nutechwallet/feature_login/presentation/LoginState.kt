package com.algirm.nutechwallet.feature_login.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val textEmail: String = "",
    val textPassword: String = "",
    val isLoading: Boolean = false,
) : Parcelable
