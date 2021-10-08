package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.superhero.Status
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.repositry.HeroRepositry
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQueryResult("Batman")

    }

    private fun getHeroReusltsById(id: Int){
        lifecycleScope.launch {
            HeroRepositry.getSuperResults(id).collect{getSuperHeroById(it)}
        }
    }

    private fun getSuperHeroById(status: Status<SuperHero>) {
        when (status) {
            is Status.Error -> {
                Log.i(TAG,"error ${status.message}")
            }
            is Status.Loading -> {
                Log.i(TAG,"loading")
            }
            is Status.Success -> {
                Log.i(TAG,"sucess ${status.data.name}")
            }
        }
    }

    private fun getQueryResult(searchQuery:String){
        lifecycleScope.launch {
            HeroRepositry.getQueryResults(searchQuery).collect { getSearchResponceStatus(it) }
        }
    }
    private fun getSearchResponceStatus(status:Status<SearchResponse>) {
        when (status) {
            is Status.Error -> {
                Log.i(TAG,"error ${status.message}")
            }
            Status.Loading -> {
                Log.i(TAG,"loading")
            }
            is Status.Success -> {
//                Log.i(TAG,"sucess ${status.data.listOfResults[0].biography}")
                getHeroReusltsById(status.data.listOfResults[0].id!!.toInt())
            }
        }
    }
    companion object{
        const val TAG="Hero"
    }
}

