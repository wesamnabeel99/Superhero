package com.example.superhero.repositry

import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object HeroRepositry {
    fun getName(superHeroName:String) = flow<Status<SuperHero>>{
        emit(Status.Loading)
        emit(Client.makeNameRequest(superHeroName))
    }.flowOn(Dispatchers.IO)

}


