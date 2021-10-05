package com.example.superhero.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superhero.R
import com.example.superhero.databinding.FragmentHomeBinding
import com.example.superhero.util.Constant

class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding=FragmentHomeBinding::inflate
    override fun setup() {
        log(LOG_TAG)
    }

    override fun addCallbacks() {
        binding?.editTextSearch?.setOnEditorActionListener { textView, i, keyEvent ->
            if (keyEvent!=null && keyEvent.keyCode==KeyEvent.KEYCODE_ENTER)
                addResultFragment(textView.text.toString())
            false
        }
        }

    private fun addResultFragment(heroName: String) {
        binding?.searchAnimation?.visibility=View.GONE
        val resultFragment=ResultFragment()
        val bundle=Bundle()
        bundle.putString(Constant.HERO_NAME,heroName)
        resultFragment.arguments=bundle
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragment_container_result,resultFragment).commit()
    }
}