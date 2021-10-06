package com.example.superhero.model


import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affiliation")
    var groupAffiliation: String? = "",
    @SerializedName("relatives")
    var relatives: String? = ""
)