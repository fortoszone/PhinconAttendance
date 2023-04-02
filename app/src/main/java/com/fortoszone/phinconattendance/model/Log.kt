package com.fortoszone.phinconattendance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Log(
    var isCheckedIn: Boolean = false,
    var image: Int? = 0,
    var site: String? = "",
    var address: String? = "",
    var time: String? = ""
) : Parcelable