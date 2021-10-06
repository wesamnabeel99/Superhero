package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("eye-color")
    var eyeColor: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("hair-color")
    var hairColor: String? = "",
    @SerializedName("height")
    var height: List<String>? = listOf(),
    @SerializedName("race")
    var race: String? = "",
    @SerializedName("weight")
    var weight: List<String>? = listOf()
)