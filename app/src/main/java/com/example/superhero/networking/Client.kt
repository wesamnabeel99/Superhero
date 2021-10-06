package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import com.example.superhero.util.Constant
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Client:IClient {
    private val okHttpClient = OkHttpClient()

    private val responseObject = Response()

    override fun buildUrl(superHeroName:String)=HttpUrl.Builder()
        .scheme(Constant.SCHEMA)
        .host(Constant.HOST)
        .addPathSegment(Constant.API)
        .addPathSegment(Constant.TOKEN)
        .addPathSegment(Constant.SEARCH)
        .addPathSegment(superHeroName)
        .build()

    override fun makeNameRequest(superHeroName: String): Status<SuperHero> {
        val request=Request.Builder()
            .url(buildUrl(superHeroName))
            .build()
        val responseName = okHttpClient.newCall(request).execute()
        return responseObject.responseNameStatus(responseName)
    }




}