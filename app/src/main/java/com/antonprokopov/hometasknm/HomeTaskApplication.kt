package com.antonprokopov.androidhometask

import android.app.Application
import com.antonprokopov.core.di.ParentComponentProvider

class HomeTaskApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ParentComponentProvider.appComponent = createComponent()
    }

    private fun createComponent(): MergedAppComponent {
        val component = DaggerMergedAppComponent.builder()
            .mergedAppModule(MergedAppModule(applicationContext))
            .build()
        return component

    }
}