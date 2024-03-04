package com.antonprokopov.androidhometask

import android.content.Context
import com.antonprokopov.breedsfeed.di.BreedsFeedSharedModule
import dagger.Module
import dagger.Provides

@Module(includes = [BreedsFeedSharedModule::class])
class MergedAppModule(private val appContext: Context) {
    @Provides
    internal fun provideContext(): Context {
        return appContext
    }
}