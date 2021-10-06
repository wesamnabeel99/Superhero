package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.superhero.R
import com.example.superhero.Status
import com.example.superhero.databinding.ActivityMainBinding
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

    }

       private fun getHeroResult(superHeroName:String){
           lifecycleScope.launch {
             HeroRepositry.getName(superHeroName).collect { getResult(it) }
           }
       }
    private fun getResult(status:Status<SuperHero>) {
        when (status) {
            is Status.Error -> {


            }
            Status.Loading -> {
            }
            is Status.Success -> {
                Log.i(TAG,"sucess")

            }
        }
    }
    companion object{
        const val TAG="Hero"
    }
}

