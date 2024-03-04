package com.antonprokopov.breedsfeed.ui.albumslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.antonprokopov.breedsfeed.databinding.ItemBreedBinding
import com.antonprokopov.core.extensions.toLongRepresentation

class BreedsAdapter(context: Context) : ListAdapter<String, BreedViewHolder>(diffCallback) {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = BreedViewHolder(
        ItemBreedBinding.inflate(inflater, parent, false)
    )

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int) = getItem(position).toLongRepresentation()

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(old: String, new: String) = old.toLongRepresentation() == new.toLongRepresentation()
            override fun areContentsTheSame(old: String, new: String) = old == new
        }
    }
}