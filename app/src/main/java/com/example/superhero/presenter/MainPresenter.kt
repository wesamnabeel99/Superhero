package com.example.superhero.presenter

import android.util.Log
import com.example.superhero.Status
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero

import com.example.superhero.networking.Client
import com.example.superhero.networking.ResponseType
import com.example.superhero.ui.IMainView
import com.example.superhero.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainPresenter(private val view: IMainView) {

    inline fun <reified T> emitRequestResult(urlSegment: String, responseType: ResponseType) = flow {
        emit(Status.Loading)
        emit(Client.makeRequest<T>(urlSegment,responseType))
    }.flowOn(Dispatchers.IO)


    fun <T> getRequestStatus(status: Status <T> , responseType: ResponseType) {
        when (status) {
            is Status.Error -> {
                Log.i(MainActivity.TAG, "error ${status.message}")
            }
            is Status.Loading -> {
                Log.i(MainActivity.TAG, "loading")
            }
            is Status.Success -> {
                when (responseType) {
                    ResponseType.SuperHero -> view.onSuperHeroSuccess(status.data as SuperHero)
                    ResponseType.SearchResponse -> view.onSearchQuerySuccess(status.data as SearchResponse)
                }

            }
        }
    }

}


