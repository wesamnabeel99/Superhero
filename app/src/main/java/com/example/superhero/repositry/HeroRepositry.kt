package com.example.superhero.repositry

import com.example.superhero.Status
import com.example.superhero.networking.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object HeroRepositry {
    fun getQueryResults (searchQuery:String) = flow{
        emit(Status.Loading)
        emit(Client.makeNameRequest(searchQuery))
    }.flowOn(Dispatchers.IO)

}


