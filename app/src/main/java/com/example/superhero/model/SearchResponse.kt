package com.example.superhero.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("response")
    val response : String,
    @SerializedName("results-for")
    val resultFor : String,
    @SerializedName("results")
    val listOfResults : ArrayList<SuperHero>
)