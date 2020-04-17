package com.wattpad.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    val id: String,
    val cover: String?,
    val title: String?,
    val user: User?
): Parcelable