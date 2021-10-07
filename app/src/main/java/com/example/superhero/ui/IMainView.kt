package com.example.superhero.ui

import com.example.superhero.model.SuperHero

interface IMainView {
    fun onSuperHeroSuccess(superHero: SuperHero)
}