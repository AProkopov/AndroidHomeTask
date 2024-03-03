package com.antonprokopov.albumsfeed.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonprokopov.albumsfeed.data.models.BreedsListModel
import com.antonprokopov.albumsfeed.di.AlbumsFeedComponentHolder
import com.antonprokopov.albumsfeed.usecase.BreedsUseCase
import com.antonprokopov.core.data.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumsViewModel : ViewModel() {

    @Inject
    lateinit var breedsUseCase: BreedsUseCase

    init {
        AlbumsFeedComponentHolder.getComponent()?.inject(this)
    }

    val albumsDataLiveData = MutableLiveData<BreedsListModel>()
    val loadingStateLiveData = MutableLiveData<Boolean>()
    val errorStateLiveData = MutableLiveData<Resource.ErrorDesc>()

    fun getAlbumPreviews() {
        viewModelScope.launch {
            breedsUseCase.execute()
                .catch { e ->
                    errorStateLiveData.value = Resource.ErrorDesc(message = e.message)
                }
                .collect {
                    loadingStateLiveData.value = it is Resource.Loading

                    when (it) {
                        is Resource.Success -> albumsDataLiveData.value = it.data
                        is Resource.Error -> errorStateLiveData.value = it.desc
                        is Resource.Loading -> {
                            //do nothing
                        }
                    }
                }
        }
    }
}