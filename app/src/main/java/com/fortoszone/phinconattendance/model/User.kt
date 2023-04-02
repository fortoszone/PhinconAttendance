package com.fortoszone.phinconattendance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val isCheckIn: Boolean = false
) : Parcelable
