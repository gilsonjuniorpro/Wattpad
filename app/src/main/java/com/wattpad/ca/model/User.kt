package com.wattpad.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val avatar: String?,
    val fullname: String?,
    val name: String?
): Parcelable