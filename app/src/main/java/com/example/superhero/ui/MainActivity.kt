package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.superhero.Status
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.presenter.MainPresenter
import com.example.superhero.repositry.HeroRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),IMainView {
    lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSuperHeroSuccess(superHero: SuperHero) {
        //Bind Data to RecyclerView

    }

    private fun getHeroReusltsById(id: Int) {
        lifecycleScope.launch {
            presenter.getSuperResults(id).collect { presenter.getSuperHeroById(it) }
        }
    }


    private fun getSearchResponseStatus(status: Status<SearchResponse>) {
        when (status) {
            is Status.Error -> {
                Log.i(TAG, "error ${status.message}")
            }
            Status.Loading -> {
                Log.i(TAG, "loading")
            }
            is Status.Success -> {
//                Log.i(TAG,"sucess ${status.data.listOfResults[0].biography}")
                getHeroReusltsById(status.data.listOfResults[0].id!!.toInt())
            }
        }
    }

    companion object {
        const val TAG = "Hero"
    }
}

