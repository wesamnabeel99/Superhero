package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Work(
    @SerializedName("base")
    var base: String? = "",
    @SerializedName("occupation")
    var occupation: String? = ""
)