package com.example.superhero.presenter

import android.util.Log
import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero

import com.example.superhero.networking.Client1
import com.example.superhero.networking.ResponseType
import com.example.superhero.ui.IMainView
import com.example.superhero.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainPresenter(private val view: IMainView) {

    inline fun <reified T> emitRequestResult(searchQuery: String, responseType: ResponseType) = flow {
        emit(Status.Loading)
        emit(Client1.makeSuperHeroRequest<T>(searchQuery,responseType))
    }.flowOn(Dispatchers.IO)


    fun getSuperHeroById(status: Status<SuperHero>) {
        when (status) {
            is Status.Error -> {
                Log.i(MainActivity.TAG, "error ${status.message}")
            }
            is Status.Loading -> {
                Log.i(MainActivity.TAG, "loading")
            }
            is Status.Success -> {
                view.onSuperHeroSuccess(status.data)
            }
        }
    }

    fun getSearchQuery(status: Status<SearchResponse>) {
        when (status) {
            is Status.Error -> {
                Log.i(MainActivity.TAG, "error ${status.message}")
            }
            is Status.Loading -> {
                Log.i(MainActivity.TAG, "loading")
            }
            is Status.Success -> {
                view.onSearchQuerySuccess(status.data)
            }
        }
    }
}


