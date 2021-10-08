package com.example.superhero.ui.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superhero.R
import com.example.superhero.model.SuperHero
import com.example.superhero.ui.HeroInteractionListener
import com.example.superhero.ui.ViewHolders.HeroViewHolder

class HeroAdapter(val heroList:List<SuperHero>?,val listener:HeroInteractionListener):RecyclerView.Adapter<HeroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero,parent,false)
        return HeroViewHolder(view)    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        heroList?.get(position).apply {
            Glide.with(holder.heroImage.context).load(this?.image?.url).into(holder.heroImage)
            heroList?.apply { holder.heroImage.setOnClickListener { listener.onClickItem(this[position]) } }
        }
    }

    override fun getItemCount()= heroList?.size ?: 0

}