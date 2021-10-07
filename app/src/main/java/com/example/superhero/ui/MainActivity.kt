package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.superhero.Status
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.presenter.MainPresenter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , IMainView {
    val presenter = com.example.superhero.presenter.MainPresenter(this)
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQueryResult("Batman")

    }

    private fun getHeroReusltsById(id: Int){
        lifecycleScope.launch {
            presenter.getSuperResults(id).collect{presenter.getSuperHeroById(it)}
        }
    }



    private fun getQueryResult(searchQuery:String){
           lifecycleScope.launch {
             presenter.getQueryResults(searchQuery).collect { presenter.getSearchResponceStatus(it) }
           }
       }

    companion object{
        const val TAG="Hero"
    }

    override fun onSearchQuerySuccess(searchResponse: SearchResponse) {
        Log.i(TAG,"I got this from search ${searchResponse.listOfResults[0].name}")

        getHeroReusltsById(searchResponse.listOfResults[0].id!!.toInt())
    }

    override fun onSuperHeroSuccess(superHero: SuperHero) {
        Log.i(MainActivity.TAG,"I got this super hero! ${superHero.name}")
        Toast.makeText(this,"I got the super hero! ${superHero.name} !",Toast.LENGTH_LONG).show()
    }
}

