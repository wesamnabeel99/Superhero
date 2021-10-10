package com.example.superhero.networking

import com.example.superhero.Status
import okhttp3.Response

object ResponseStatus {

    inline fun <reified T> getResponseStatus(response: Response): Status<T> {
        return if (response.isSuccessful) {
            val parsedResponse = JsonParser.parseTheResponse<T>(response)
            Status.Success(parsedResponse)
        } else {
            Status.Error(response.message)
        }
    }


}