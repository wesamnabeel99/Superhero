package com.example.superhero.ui

import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero

interface IMainView {
    fun onSearchQuerySuccess(searchResponse: SearchResponse)
    fun onSuperHeroSuccess(superHero: SuperHero)
}