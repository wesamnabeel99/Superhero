package com.example.superhero.util

import com.example.superhero.model.SuperHero

interface FragmentCommunicator {
    fun passDataBetweenFragments (superHero: SuperHero)
}