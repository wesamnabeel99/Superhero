package com.example.superhero.ui

import com.example.superhero.model.SuperHero

interface HeroInteractionListener {
    fun onClickItem(hero:SuperHero)
}