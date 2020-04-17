package com.wattpad.ca.service

import com.wattpad.ca.model.WattpadResponse
import com.wattpad.ca.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.wattpad.com/api/v3/stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new
interface WattpadInterface {
    @GET("stories?")
    fun getStories(
        @Query("offset") offset: Int = Constants.OFFSET,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("fields") fields: String = Constants.FIELDS,
        @Query("filter") filter: String = Constants.FILTER
    ): Call<WattpadResponse>
}