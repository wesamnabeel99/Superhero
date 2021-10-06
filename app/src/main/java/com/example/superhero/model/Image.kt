package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    var url: String? = ""
)