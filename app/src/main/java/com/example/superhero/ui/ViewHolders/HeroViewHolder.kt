package com.example.superhero.ui.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.R
import com.google.android.material.imageview.ShapeableImageView

class HeroViewHolder(mView:View):RecyclerView.ViewHolder(mView){
    val heroImage:ShapeableImageView=mView.findViewById(R.id.imageHero)
}