package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.superhero.R
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.model.SuperHero
import com.example.superhero.ui.fragments.HomeFragment
import com.example.superhero.ui.fragments.ResultFragment
import com.example.superhero.ui.interfaces.FragmentCommunicator

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment(fragmentCommunicator = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(homeFragment)
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_home,fragment).commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragment_container_home,fragment).commit()
    }

    override fun passDataBetweenFragments(data: String) {
        replaceFragment(ResultFragment.createNewInstance(data))
    }

    companion object {
        const val TAG = "Hero"
    }



}

