package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.ResponseType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IMainView {
    val presenter = com.example.superhero.presenter.MainPresenter(this)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQueryResult("Batman")
    }


    private fun getQueryResult(searchQuery: String) {
        lifecycleScope.launch {
            presenter.emitRequestResult<SearchResponse>(searchQuery,responseType = ResponseType.SearchResponse).collect { presenter.getSearchQuery(it) }
        }
    }

    private fun getHeroReusltsById(id: String) {
        lifecycleScope.launch {
            presenter.emitRequestResult<SuperHero>(id,responseType = ResponseType.SuperHero).collect { presenter.getSuperHeroById(it) }
        }
    }

    companion object {
        const val TAG = "Hero"
    }

    override fun onSearchQuerySuccess(searchResponse: SearchResponse) {

        Log.i(TAG, "${ searchResponse.listOfResults.count()}")
        searchResponse.listOfResults.forEach {
            Log.i(TAG, "${it.name}")
        }
        getHeroReusltsById(searchResponse.listOfResults[0].id!!)
    }

    override fun onSuperHeroSuccess(superHero: SuperHero) {
        Log.i(TAG, "I got this super hero! ${superHero.name}")
        Toast.makeText(this, "I got the super hero! ${superHero.name} !", Toast.LENGTH_LONG).show()
    }
}

