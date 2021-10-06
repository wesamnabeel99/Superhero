package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class SuperHero(
    @SerializedName("appearance")
    var appearance: Appearance? = Appearance(),
    @SerializedName("biography")
    var biography: Biography? = Biography(),
    @SerializedName("connections")
    var connections: Connections? = Connections(),
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("image")
    var image: Image? = Image(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("powerstats")
    var powerstats: Powerstats? = Powerstats(),
    @SerializedName("response")
    var response: String? = "",
    @SerializedName("work")
    var work: Work? = Work()
)