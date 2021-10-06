package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.google.gson.Gson
import okhttp3.Response

object CheckResponse {
     fun responseNameStatus(response: Response): Status<SearchResponse> {
        return if (response.isSuccessful) {
          val parserResponse = Gson().fromJson(
               response.body?.string(),
               SearchResponse::class.java
           )
           Status.Success(parserResponse)
        } else {
            Status.Error(response.message)
        }
    }

}