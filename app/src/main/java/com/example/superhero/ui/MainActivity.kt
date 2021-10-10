package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.superhero.R
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.ResponseType
import com.example.superhero.presenter.MainPresenter
import com.example.superhero.util.FragmentCommunicator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),FragmentCommunicator {

    lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(homeFragment)
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_home,fragment)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragment_container_home,fragment).commit()
    }


    companion object {
        const val TAG = "Hero"
    }

    override fun passDataBetweenFragments(superHero: SuperHero) {
        replaceFragment(ResultFragment.createNewInstance(superHero))
    }


}

