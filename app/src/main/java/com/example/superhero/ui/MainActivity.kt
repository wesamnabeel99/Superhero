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
import com.example.superhero.presenter.MainPresenter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IMainView {
    val presenter = MainPresenter(this)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        search("Batman")
    }

    private fun search(query: String) {
        collectResult<SearchResponse>(query,ResponseType.SearchResponse)
    }


      private inline fun <reified T> collectResult(urlSegment: String, responseType : ResponseType) {
        lifecycleScope.launch {
            presenter.emitRequestResult<T>(urlSegment,responseType)
                .collect {
                    presenter.run {
                        getRequestStatus(status = it, responseType)
                    }
                }
        }
    }


    override fun onSearchQuerySuccess(searchResponse: SearchResponse) {
        //TODO : show results on recycler view
        collectResult <SuperHero> (searchResponse.listOfResults[0].id!!,ResponseType.SuperHero)

        Log.i(TAG, "${ searchResponse.listOfResults.count()}")
        searchResponse.listOfResults.forEach {
            Log.i(TAG, "${it.name}")
        }

    }

    override fun onSuperHeroSuccess(superHero: SuperHero) {
        //TODO : when you get the SuperHero data pass it to the biography fragment
        Log.i(TAG, "I got this super hero! ${superHero.name}")
        Toast.makeText(this, "I got the super hero! ${superHero.name} !", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "Hero"
    }

}

