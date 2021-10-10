package com.example.superhero.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superhero.databinding.FragmentResultBinding
import com.example.superhero.model.SuperHero
import com.example.superhero.presenter.BasePresenter
import com.example.superhero.presenter.ResultPresenter
import com.example.superhero.ui.interfaces.IResultView
import com.example.superhero.util.Constant

class ResultFragment : BaseFragment<FragmentResultBinding , ResultPresenter>() , IResultView {

    lateinit var superHero : SuperHero
    override val presenterType = ResultPresenter()

    override val LOG_TAG: String = "RESULT_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding =
        FragmentResultBinding::inflate

    override fun onStart() {
        super.onStart()
    }

    override fun setup() {
        superHero = arguments?.getSerializable(Constant.HERO_NAME) as SuperHero
        bindSuperHeroData(superHero)
    }

    private fun bindSuperHeroData(superHero: SuperHero) {

        binding?.apply {
            textHeroName.text = superHero.name.toString()
            textDescription.text = superHero.appearance.toString()
        }
    }

    override fun addCallbacks() {

    }

    companion object {
        fun createNewInstance(superHero: SuperHero): ResultFragment {
            return ResultFragment().apply {
                arguments= Bundle().apply {
                    putSerializable(Constant.HERO_NAME,superHero)
                }
            }
        }
    }

    override fun <T> onSuccess(response: T) {

    }
}