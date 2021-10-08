package com.example.superhero.networking

import com.google.gson.Gson
import okhttp3.Response

object JsonParser {
    inline fun <reified T> parseTheResponse (response : Response): T = Gson().fromJson(
        response.body?.string(),
        T::class.java)
}