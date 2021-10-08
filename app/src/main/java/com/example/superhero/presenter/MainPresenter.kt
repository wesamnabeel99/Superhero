package com.example.superhero.presenter

import android.util.Log
import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.Client
import com.example.superhero.ui.IMainView
import com.example.superhero.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainPresenter(private val view: IMainView) {

    fun getQueryResults(searchQuery: String) = flow {
        emit(Status.Loading)
        emit(Client.getQueryReuslts(searchQuery))
    }.flowOn(Dispatchers.IO)

    fun getSearchResponseStatus(status: Status<SearchResponse>) {
        when (status) {
            is Status.Error -> {
                Log.i(MainActivity.TAG, "error ${status.message}")
            }
            Status.Loading -> {
                Log.i(MainActivity.TAG, "loading")
            }
            is Status.Success -> {
                view.onSearchQuerySuccess(status.data)
            }
        }
    }

    fun getSuperResults(id: Int) = flow {
        emit(Status.Loading)
        emit(Client.getSuperHeroDataById(id))
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
}


