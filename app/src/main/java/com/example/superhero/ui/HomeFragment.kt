package com.example.superhero.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.superhero.R
import com.example.superhero.Status
import com.example.superhero.databinding.FragmentHomeBinding
import com.example.superhero.model.SearchResponse
import com.example.superhero.model.SuperHero
import com.example.superhero.networking.Client
import com.example.superhero.repositry.HeroRepositry
import com.example.superhero.ui.Adapters.HeroAdapter
import com.example.superhero.util.Constant
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding=FragmentHomeBinding::inflate
    override fun setup() {
        log(LOG_TAG)
    }

    override fun addCallbacks() {
        binding?.editTextSearch?.setOnEditorActionListener { textView, i, keyEvent ->
            if (keyEvent!=null && keyEvent.keyCode==KeyEvent.KEYCODE_ENTER){
                hideSearchView()
                showCharactersImage(textView.text.toString())
            }

            false
        }
    }

    private fun showCharactersImage(heroName: String) {
        lifecycleScope.launch {
            HeroRepositry.getQueryResults(heroName).collect(::bindCharactersImages)
        }
    }

    private fun bindCharactersImages(responseStatus:Status<SearchResponse>) {
        when (responseStatus) {
            is Status.Error -> binding?.apply {
                Log.i("Hamada",responseStatus.message)
                recyclerCharacters.visibility=View.GONE
                searchAnimation.setAnimation(R.raw.not_found_animation)
                searchAnimation.visibility=View.VISIBLE
            }
            Status.Loading -> binding?.searchAnimation?.apply {  visibility=View.VISIBLE
                Log.i("Hamada","Loading")
                setAnimation(R.raw.loading_animation)}
            is Status.Success -> binding?.apply {
                searchAnimation.visibility=View.GONE
                recyclerCharacters.adapter = HeroAdapter(responseStatus.data.listOfResults,object :HeroInteractionListener{
                    override fun onClickItem(hero: SuperHero) {
                        lifecycleScope.launch{
                            HeroRepositry.getSuperResults(hero.id!!.toInt()).collect(::checkHeroData)
                        }
                    }

                })
                recyclerCharacters.visibility = View.VISIBLE
            }
        }
    }

    private fun checkHeroData(superHeroData: Status<SuperHero>) {
        when(superHeroData){
            is Status.Error -> Log.i("Hamada",superHeroData.message)
            Status.Loading -> Log.i("Hamada","Loading")
            is Status.Success -> bindHeroData(superHeroData.data)
        }
    }

    private fun bindHeroData(data: SuperHero) {
        binding?.recyclerCharacters?.visibility=View.GONE
        addResultFragment(data)
    }

    private fun addResultFragment(heroData: SuperHero) {
        val resultFragment=ResultFragment()
        val bundle=Bundle()
        bundle.putSerializable(Constant.HERO_DATA,heroData)
        resultFragment.arguments=bundle
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragment_container_result,resultFragment).addToBackStack("hamada").commit()
    }

    private fun hideSearchView() {
        binding?.searchAnimation?.visibility=View.GONE
    }

}