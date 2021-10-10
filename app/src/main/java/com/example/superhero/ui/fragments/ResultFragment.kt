package com.example.superhero.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.superhero.databinding.FragmentResultBinding
import com.example.superhero.model.SuperHero
import com.example.superhero.presenter.ResultPresenter
import com.example.superhero.ui.interfaces.IResultView
import com.example.superhero.util.Constant
import com.example.superhero.util.ResponseType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResultFragment : BaseFragment<FragmentResultBinding, ResultPresenter>() , IResultView {

    lateinit var superHeroId : String
    override val presenterType = ResultPresenter()

    override val LOG_TAG: String = "RESULT_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding =
        FragmentResultBinding::inflate

    override fun setup() {
        presenterType.view = this
        superHeroId = arguments?.getString(Constant.SUPER_HERO_KEY).toString()
        makeSuperHeroRequestById()
    }
    private fun makeSuperHeroRequestById () {
        collectResult<SuperHero>(superHeroId,ResponseType.SuperHero)
    }

    private inline fun <reified T> collectResult(urlSegment: String, responseType : ResponseType) {
        lifecycleScope.launch {
            presenterType.emitRequestResult<T>(urlSegment,responseType)
                .collect {
                    presenterType.getRequestStatus(status = it, responseType)
                }
        }
    }


    override fun <T> onSuccess(response: T) {
        bindSuperHeroData(response as SuperHero)
    }

    private fun bindSuperHeroData(superHero: SuperHero) {

        binding?.apply {
            textHeroName.text = superHero.name.toString()
            textDescription.text = superHero.appearance.toString()
        }
    }

    override fun addCallbacks() {
        //TODO : add callbacks
    }

    companion object {
        fun createNewInstance(data: String) = ResultFragment().apply {
            arguments= Bundle().apply {
                putString(Constant.SUPER_HERO_KEY,data)
            }
        }

    }
}