package com.example.superhero.networking

import android.util.Log
import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.google.gson.Gson
import okhttp3.Response

object CheckResponse {
    fun responseNameStatus(response: Response): Status<SearchResponse> {
        val parserResponse = Gson().fromJson(
            response.body?.string(),
            SearchResponse::class.java
        )
        return if (parserResponse.response=="error")
            Status.Error(response.message)
        else
            Status.Success(parserResponse)

    }

    fun responseIdStatus(response: Response): Status<SuperHero> {
        return if (response.isSuccessful) {
            val parserResponse = Gson().fromJson(
                response.body?.string(),
                SuperHero::class.java
            )
            Status.Success(parserResponse)
        } else {
            Status.Error(response.message)
        }
    }

}