package com.example.superhero.networking

import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import com.google.gson.Gson
import okhttp3.Response

class Response :IResponse{
    override fun responseNameStatus(response: Response): Status<SuperHero> {
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