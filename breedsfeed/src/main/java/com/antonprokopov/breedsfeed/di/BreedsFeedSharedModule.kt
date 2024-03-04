package com.antonprokopov.breedsfeed.di

import com.antonprokopov.breedsfeed.ui.BreedsFeedRouterImpl
import com.antonprokopov.breedsfeedapi.route.BreedsFeedRouter
import dagger.Module
import dagger.Provides

@Module
class BreedsFeedSharedModule {

    @Provides
    fun provideMyOffersRouter(): BreedsFeedRouter = BreedsFeedRouterImpl()
}