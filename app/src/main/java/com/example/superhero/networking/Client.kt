package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


object Client {
    lateinit var url: HttpUrl
    inline fun <reified T> makeSuperHeroRequest(segment: String): Status<T> {
        val checkClass = T::class.java
        url = when {
            checkClass.isAssignableFrom(SearchResponse::class.java) -> {
                HttpBuilder.buildSearchUrl(segment)
            }
            else -> {
                HttpBuilder.buildSuperHeroUrl(segment)
            }
        }

        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        return ResponseStatus.checkResponseStatus(response)
    }
}


object Client1 {
    lateinit var url: HttpUrl
    inline fun <reified T> makeSuperHeroRequest(
        segment: String,
        requestType: RequestType
    ): Status<T> {

        url = when (requestType) {
            RequestType.SuperHero -> HttpBuilder.buildSearchUrl(segment)
            RequestType.SearchResponse -> HttpBuilder.buildSuperHeroUrl(segment)
        }

        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        return ResponseStatus.checkResponseStatus(response)
    }

}


