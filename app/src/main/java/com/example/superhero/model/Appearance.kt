package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("eye-color")
    var eyeColor: String? = "",
    var gender: String? = "",
    @SerializedName("hair-color")
    var hairColor: String? = "",
    var height: List<String>? = listOf(),
    var race: String? = "",
    var weight: List<String>? = listOf()
)