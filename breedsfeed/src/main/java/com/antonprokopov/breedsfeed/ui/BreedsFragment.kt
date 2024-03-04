package com.antonprokopov.breedsfeed.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antonprokopov.breedsfeed.databinding.FragmentBreedsFeedBinding
import com.antonprokopov.breedsfeed.di.BreedsFeedComponentHolder
import com.antonprokopov.core.ui.BaseViewBindingFragment
import javax.inject.Inject

class BreedsFragment : BaseViewBindingFragment<FragmentBreedsFeedBinding>() {

    companion object {
        const val TAG = "AlbumsFragment"
    }

    @Inject
    lateinit var breedsUi: BreedsUi

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToRoot: Boolean,
        savedInstanceState: Bundle?
    ): FragmentBreedsFeedBinding {
        return FragmentBreedsFeedBinding.inflate(inflater, container, attachedToRoot)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
            .also { breedsUi.fragmentViewBinding = this.viewBinding }
            .also { breedsUi.initUI() }
    }

    override fun onAttach(context: Context) {
        BreedsFeedComponentHolder.initComponent().inject(this)
        super.onAttach(context)
    }

    override fun onDetach() {
        BreedsFeedComponentHolder.releaseComponent()
        super.onDetach()
    }
}