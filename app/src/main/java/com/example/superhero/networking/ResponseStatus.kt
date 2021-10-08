package com.example.superhero.networking

import com.example.superhero.Status
import okhttp3.Response

object ResponseStatus {

    inline fun <reified T> checkResponseStatus(response: Response): Status<T> {
        return if (response.isSuccessful) {
            val parserResponse = JsonParser.parseTheResponse<T>(response)
            Status.Success(parserResponse)
        } else {
            Status.Error(response.message)
        }
    }


}