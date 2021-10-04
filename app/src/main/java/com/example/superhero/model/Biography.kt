package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Biography(
    @SerializedName("aliases")
    var aliases: List<String>? = listOf(),
    @SerializedName("alignment")
    var alignment: String? = "",
    @SerializedName("alter-egos")
    var alterEgos: String? = "",
    @SerializedName("first-appearance")
    var firstAppearance: String? = "",
    @SerializedName("full-name")
    var fullName: String? = "",
    @SerializedName("place-of-birth")
    var placeOfBirth: String? = "",
    @SerializedName("publisher")
    var publisher: String? = ""
)