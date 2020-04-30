package com.wattpad.ca.controller

import com.wattpad.ca.model.WattpadResponse
import com.wattpad.ca.service.WattpadInterface
import com.wattpad.ca.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WattpadController {
    companion object{
        private fun getRetrofit(): WattpadInterface? {
            val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WattpadInterface::class.java)
        }

        fun getStoriesCall(offset: Int, limit: Int, fields: String, filter: String): Call<WattpadResponse>? {
            return getRetrofit()?.getStories(offset, limit, fields, filter)
        }
    }
}