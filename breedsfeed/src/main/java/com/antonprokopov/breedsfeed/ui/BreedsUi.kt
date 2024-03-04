package com.antonprokopov.breedsfeed.ui

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonprokopov.breedsfeed.R
import com.antonprokopov.breedsfeed.databinding.FragmentBreedsFeedBinding
import com.antonprokopov.breedsfeed.ui.albumslist.BreedsAdapter
import com.antonprokopov.breedsfeed.viewmodel.BreedsViewModel
import com.antonprokopov.core.extensions.gone
import com.antonprokopov.core.extensions.setVisibleOrGone
import com.antonprokopov.core.extensions.visible
import com.antonprokopov.core.ui.ActivityLifecycleOwnerHolder
import com.antonprokopov.core.ui.EmptyStateView
import com.antonprokopov.core.ui.ViewBindingUi
import javax.inject.Inject

class BreedsUi  @Inject constructor(
    private val activityLifecycleOwnerHolder: ActivityLifecycleOwnerHolder,
    private val albumsVm: BreedsViewModel,
    private val context: Context
): ViewBindingUi<FragmentBreedsFeedBinding>() {

    init {
        initSubscriptions()
    }

    fun initUI() {
        fragmentViewBinding?.apply {
            rvAlbums.adapter = BreedsAdapter(context)
            rvAlbums.layoutManager = LinearLayoutManager(context)
            swipeRefreshLayoutAlbums.setOnRefreshListener(::getAlbums)
            emptyStateView.setRetryCallback(::getAlbums)

            setDecorationToAlbumsList(rvAlbums)
        }

        getAlbums()
    }

    private fun initSubscriptions() {
        albumsVm.albumsDataLiveData.observe(
            activityLifecycleOwnerHolder.lifecycleOwner
        ) {
            if (it.breeds.isEmpty()) {
                showEmptyState()
            } else {
                showAlbums(it.breeds)
            }
        }

        albumsVm.loadingStateLiveData.observe(
            activityLifecycleOwnerHolder.lifecycleOwner
        ) {
            showLoadingState(it)
        }

        albumsVm.errorStateLiveData.observe(
            activityLifecycleOwnerHolder.lifecycleOwner
        ) {
            showErrorState()
        }
    }

    private fun showErrorState() {
        fragmentViewBinding?.apply {
            emptyStateView.setState(EmptyStateView.EmptyState.ERROR)
            emptyStateView.visible()
            swipeRefreshLayoutAlbums.gone()
        }
    }

    private fun showEmptyState() {
        fragmentViewBinding?.apply {
            emptyStateView.setState(EmptyStateView.EmptyState.EMPTY, R.string.breedsfeed_no_albums_text)
            emptyStateView.visible()
            swipeRefreshLayoutAlbums.gone()
        }
    }

    private fun showLoadingState(isLoading: Boolean) {
        fragmentViewBinding?.apply {
            emptyStateView.gone()
            loadingView.setVisibleOrGone(isLoading)
            swipeRefreshLayoutAlbums.isRefreshing = false
        }
    }

    private fun showAlbums(albums: List<String>) {
        fragmentViewBinding?.apply {
            emptyStateView.gone()
            swipeRefreshLayoutAlbums.visible()
            (rvAlbums.adapter as? BreedsAdapter)?.submitList(albums)
        }
    }

    private fun getAlbums() {
        albumsVm.getAlbumPreviews()
    }

    private fun setDecorationToAlbumsList(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}