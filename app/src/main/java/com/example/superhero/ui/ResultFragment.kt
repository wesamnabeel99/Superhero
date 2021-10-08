package com.example.superhero.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.superhero.databinding.FragmentResultBinding
import com.example.superhero.model.SuperHero
import com.example.superhero.util.Constant

class ResultFragment:BaseFragment<FragmentResultBinding>() {
    lateinit var heroName:String
    override val LOG_TAG: String="RESULT_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding=FragmentResultBinding::inflate
    override fun onStart() {
        super.onStart()

        val heroData=(arguments?.getSerializable(Constant.HERO_DATA) as SuperHero)
        Glide.with(requireContext()).load(heroData.image?.url).into(binding!!.imageHero)
        binding?.textDescription?.text=
            " aliases is  ${heroData.biography?.aliases .toString()} \n" +
                    " alignment is ${heroData.biography?.alignment}\n" +
                    " alter-Ego  ${heroData.biography?.alterEgos}\n" +
                    " first appearance ${heroData.biography?.firstAppearance}\n" +
                    " Full Name ${heroData.biography?.fullName}\n" +
                    " Place of birth ${heroData.biography?.placeOfBirth}\n " +
                    " publisher ${heroData.biography?.publisher}\n"
        binding?.textHeroName?.text=heroData.name.toString()


    }
    override fun setup() {
        log("inside")
    }

    override fun addCallbacks() {
        log("inside addcallbacks")
    }
}