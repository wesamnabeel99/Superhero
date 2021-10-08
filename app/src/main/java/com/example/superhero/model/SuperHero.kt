package com.example.superhero.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperHero(
    var appearance: Appearance? = Appearance(),
    var biography: Biography? = Biography(),
    var connections: Connections? = Connections(),
    var id: String? = "",
    var image: Image? = Image(),
    var name: String? = "",
    var powerstats: Powerstats? = Powerstats(),
    var response: String? = "",
    var work: Work? = Work()
) : Serializable