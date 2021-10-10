package com.example.superhero.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.superhero.databinding.FragmentHomeBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.ResponseType
import com.example.superhero.presenter.HomePresenter
import com.example.superhero.ui.interfaces.IHomeView
import com.example.superhero.util.FragmentCommunicator
import com.example.superhero.util.isKeyPressed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment (private val fragmentCommunicator: FragmentCommunicator)
    : BaseFragment<FragmentHomeBinding , HomePresenter>(), IHomeView {
    override val LOG_TAG: String = "HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override val presenterType = HomePresenter()

    override fun setup() {
        presenterType.view = this
        log(LOG_TAG)
    }

    override fun addCallbacks() {
        binding?.editTextSearch?.setOnEditorActionListener { query, _, keyEvent ->
            if (keyEvent.isKeyPressed()) {
                hideViews()
                search(query.text.toString())
            }
            false
        }
    }


    private fun search(query: String) {
        collectResult<SearchResponse>(query , ResponseType.SearchResponse)
    }


    private inline fun <reified T> collectResult(urlSegment: String, responseType : ResponseType) {
        lifecycleScope.launch {
            presenterType.emitRequestResult<T>(urlSegment,responseType)
                .collect {
                    presenterType.getRequestStatus(status = it, responseType)
                }
        }
    }


    private fun hideViews() {
        binding?.searchAnimation?.visibility = View.GONE
    }

    override fun <T> onSuccess(response: T) {
        val listOfSuperHeroes = (response as SearchResponse).listOfResults
        bindIntoRecycler(listOfSuperHeroes)
    }

    private fun bindIntoRecycler(listOfSuperHeroes : MutableList<SuperHero>) {
        fragmentCommunicator.passDataBetweenFragments(listOfSuperHeroes[0])
        //TODO("Not yet implemented")
    }


}

