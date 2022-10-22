package com.algirm.nutechwallet.feature_ewallet.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String?
) : Parcelable
