package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Biography(
    var aliases: List<String>? = listOf(),
    var alignment: String? = "",
    @SerializedName("alter-egos")
    var alterEgos: String? = "",
    @SerializedName("first-appearance")
    var firstAppearance: String? = "",
    @SerializedName("full-name")
    var fullName: String? = "",
    @SerializedName("place-of-birth")
    var placeOfBirth: String? = "",
    var publisher: String? = ""
)