package com.wattpad.ca.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoryEntity (
    @PrimaryKey
    val id: String,
    val cover: String?,
    val title: String?,
    val avatar: String?,
    val fullname: String?,
    val name: String?
)