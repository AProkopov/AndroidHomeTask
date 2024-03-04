package com.antonprokopov.breedsfeed.di

import androidx.lifecycle.ViewModelProvider
import com.antonprokopov.breedsfeed.BuildConfig
//import com.antonprokopov.breedsfeed.BuildConfig
import com.antonprokopov.breedsfeed.data.api.ApiService
import com.antonprokopov.breedsfeed.viewmodel.BreedsViewModel
import com.antonprokopov.core.ui.ActivityLifecycleOwnerHolder
import com.antonprokopov.network.NetworkResources
import dagger.Module
import dagger.Provides

@Module
class BreedsFeedModule {

    @BreedsFeedScope
    @Provides
    internal fun provideApiService(
        networkResources: NetworkResources
    ): ApiService {
        return networkResources.createRetrofit()
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @BreedsFeedScope
    @Provides
    fun provideLifeCycleOwnerHolder(): ActivityLifecycleOwnerHolder = ActivityLifecycleOwnerHolder()

    @BreedsFeedScope
    @Provides
    fun provideSplashVm(activityLifecycleOwnerHolder: ActivityLifecycleOwnerHolder): BreedsViewModel =
        ViewModelProvider(activityLifecycleOwnerHolder.viewModelStoreOwner).get(BreedsViewModel::class.java)
}