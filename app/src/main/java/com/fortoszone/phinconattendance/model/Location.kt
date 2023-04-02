package com.fortoszone.phinconattendance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    var image: Int = 0,
    var name: String = "",
    var address: String = "",
    val isSelected: Boolean = false
) : Parcelable
