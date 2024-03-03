package com.antonprokopov.albumsfeed.ui.albumslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonprokopov.albumsfeed.databinding.ItemBreedBinding

class BreedsAdapter(context: Context) : RecyclerView.Adapter<BreedViewHolder>() {

    private var albumItems: List<String> = emptyList()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = BreedViewHolder(
        ItemBreedBinding.inflate(inflater, parent, false)
    )

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(albumItems[position])
    }

    override fun getItemCount() = albumItems.size

    fun setList(list: List<String>) {
        albumItems = list
        notifyDataSetChanged()
    }
}