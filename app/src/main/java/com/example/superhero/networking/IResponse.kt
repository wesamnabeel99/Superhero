package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import okhttp3.Response

interface IResponse {
    fun responseNameStatus(response: Response):Status<SuperHero>
}