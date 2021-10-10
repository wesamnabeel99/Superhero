package com.example.superhero.networking

import com.example.superhero.util.Constant
import okhttp3.HttpUrl

object UrlBuilder {

    fun setUrlBasedOnResponseType(responseType:ResponseType,segment: String) = when (responseType) {
            ResponseType.SearchResponse -> {
                buildSearchUrl(segment)
            }

            ResponseType.SuperHero -> {
                buildSuperHeroUrl(segment)
            }
        }

    private fun buildBaseUrl() = HttpUrl.Builder()
        .scheme(Constant.SCHEMA)
        .host(Constant.HOST)
        .addPathSegment(Constant.API)
        .addPathSegment(Constant.TOKEN)


    private fun buildSearchUrl(segment: String) = buildBaseUrl()
        .addPathSegment(Constant.SEARCH)
        .addPathSegment(segment)
        .build()

    private fun buildSuperHeroUrl(segment: String) = buildBaseUrl()
        .addPathSegment(segment)
        .build()


}