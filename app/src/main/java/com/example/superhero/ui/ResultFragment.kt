package com.example.superhero.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.superhero.databinding.FragmentResultBinding
import com.example.superhero.util.Constant

class ResultFragment:BaseFragment<FragmentResultBinding>() {
    lateinit var heroName:String
    override val LOG_TAG: String="RESULT_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding=FragmentResultBinding::inflate
    override fun onStart() {
        super.onStart()
        heroName= arguments?.getString(Constant.HERO_NAME).toString()
    }
    override fun setup() {
        log("inside")
    }

    override fun addCallbacks() {
        log("inside addcallbacks")
    }
}