package com.algirm.nutechwallet.feature_registration.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterState(
    val textFirstName: String = "",
    val textLastName: String = "",
    val textEmail: String = "",
    val textPassword: String = "",
    val isLoading: Boolean = false,
) : Parcelable
