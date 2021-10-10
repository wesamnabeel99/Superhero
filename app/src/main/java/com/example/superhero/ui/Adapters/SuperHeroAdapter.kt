package com.example.superhero.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superhero.R
import com.example.superhero.model.SuperHero
import com.example.superhero.ui.interfaces.SuperHeroInteractionListener
import com.google.android.material.imageview.ShapeableImageView

class SuperHeroAdapter (
    private val heroList:List<SuperHero>?,
    val listener: SuperHeroInteractionListener)
    :RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_biography,parent,false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(heroViewHolder: HeroViewHolder, position: Int) {
        heroList?.get(position).apply {
            Glide.with(heroViewHolder.heroImage.context)
                .load(this?.image?.url)
                .into(heroViewHolder.heroImage)
            heroList?.apply {
                heroViewHolder.heroImage.setOnClickListener { listener.onItemClick(this[position]) } }
        }
    }

    override fun getItemCount()= heroList?.size ?: 0

    }


class HeroViewHolder(mView: View) : RecyclerView.ViewHolder(mView){
    val heroImage: ShapeableImageView = mView.findViewById(R.id.image_hero)
}