package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.util.Constant
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    private val okHttpClient = OkHttpClient()

    private fun buildUrl(superHeroName: String) = HttpUrl.Builder()
        .scheme(Constant.SCHEMA)
        .host(Constant.HOST)
        .addPathSegment(Constant.API)
        .addPathSegment(Constant.TOKEN)
        .addPathSegment(Constant.SEARCH)
        .addPathSegment(superHeroName)
        .build()

    fun getQueryReuslts(searchQuery: String): Status<SearchResponse> {
        val request = Request.Builder()
            .url(buildUrl(searchQuery))
            .build()
        val responseName = okHttpClient.newCall(request).execute()

        return CheckResponse.responseNameStatus(responseName)
    }

    fun getSuperHeroDataById(id: Int): Status<SuperHero> {
        val request = Request.Builder()
            .url("https://superheroapi.com/api/4409559309126530/$id")
            .build()
        val responseName = okHttpClient.newCall(request).execute()
        return CheckResponse.responseIdStatus(responseName)
    }
}