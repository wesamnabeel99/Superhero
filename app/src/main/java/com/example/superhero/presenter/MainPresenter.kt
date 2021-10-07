package com.example.superhero.presenter

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.superhero.repositry.HeroRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.superhero.Status
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.Client
import com.example.superhero.ui.IMainView
import com.example.superhero.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainPresenter {

    lateinit var view : IMainView

    fun getQueryResults(searchQuery: String) = flow {
        emit(Status.Loading)
        emit(Client.getQueryReuslts(searchQuery))
    }.flowOn(Dispatchers.IO)

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
                Log.i(MainActivity.TAG, "sucess ${status.data.name}")
                view.onSuperHeroSuccess(status.data)
            }
        }
    }

}