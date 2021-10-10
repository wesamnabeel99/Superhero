package com.example.superhero.networking

import com.example.superhero.Status
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


object Client {
    lateinit var url: HttpUrl
    inline fun <reified T> makeRequest(
        segment: String,
        responseType: ResponseType
    ): Status<T> {

        url = UrlBuilder.setUrlBasedOnResponseType(responseType,segment)

        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        return ResponseStatus.getResponseStatus(response)
    }

}


