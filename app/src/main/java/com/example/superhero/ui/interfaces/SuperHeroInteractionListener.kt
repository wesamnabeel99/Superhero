package com.example.superhero.ui.interfaces

import com.example.superhero.model.SuperHero

interface SuperHeroInteractionListener {
    fun onItemClick (superHero: SuperHero)
}
