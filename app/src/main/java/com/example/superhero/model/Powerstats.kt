package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Powerstats(
    var combat: String? = "",
    var durability: String? = "",
    var intelligence: String? = "",
    var power: String? = "",
    var speed: String? = "",
    var strength: String? = ""
)