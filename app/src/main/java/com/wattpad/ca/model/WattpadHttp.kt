package com.wattpad.ca.model

import com.google.gson.Gson
import com.wattpad.ca.util.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object WattpadHttp {
    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchStories() : WattpadResponse?{
        val request = Request.Builder()
            .url(String.format(Constants.FULL_URL))
            .build()

        try{
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, WattpadResponse::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}