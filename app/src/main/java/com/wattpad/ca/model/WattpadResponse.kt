package com.wattpad.ca.model

data class WattpadResponse(
    val nextUrl: String,
    val stories: List<Story>
)