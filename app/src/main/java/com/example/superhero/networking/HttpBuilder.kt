package com.example.superhero.networking

import com.example.superhero.util.Constant
import okhttp3.HttpUrl

object HttpBuilder {

    private fun buildBaseUrl() = HttpUrl.Builder()
        .scheme(Constant.SCHEMA)
        .host(Constant.HOST)
        .addPathSegment(Constant.API)
        .addPathSegment(Constant.TOKEN)


    fun buildSearchUrl(segment: String) = buildBaseUrl()
        .addPathSegment(Constant.SEARCH)
        .addPathSegment(segment)
        .build()

    fun buildSuperHeroUrl(segment: String) = buildBaseUrl()
        .addPathSegment(segment)
        .build()

}