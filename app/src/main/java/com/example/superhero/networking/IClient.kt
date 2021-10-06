package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import okhttp3.HttpUrl

interface IClient {
    fun buildUrl(superHeroName:String): HttpUrl
    fun makeNameRequest(superHeroName:String):Status<SuperHero>
}