package com.wattpad.ca.service

import com.wattpad.ca.model.WattpadResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WattpadInterface {
    @GET("stories?")
    fun getStories(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("fields") fields: String,
        @Query("filter") filter: String
    ): Call<WattpadResponse>
}