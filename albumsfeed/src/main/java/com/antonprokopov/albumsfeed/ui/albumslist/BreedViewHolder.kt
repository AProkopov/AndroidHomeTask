package com.antonprokopov.albumsfeed.ui.albumslist

import androidx.recyclerview.widget.RecyclerView
import com.antonprokopov.albumsfeed.databinding.ItemBreedBinding

class BreedViewHolder(itemViewBinding: ItemBreedBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

    private val breedName = itemViewBinding.breedName

    fun bind(name: String) {
        breedName.text = name
    }
}