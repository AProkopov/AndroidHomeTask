package com.antonprokopov.breedsfeed.di

import com.antonprokopov.breedsfeed.ui.BreedsActivity
import com.antonprokopov.breedsfeed.ui.BreedsFragment
import com.antonprokopov.breedsfeed.viewmodel.BreedsViewModel
import com.antonprokopov.core.di.ParentComponentProvider
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class BreedsFeedScope

@BreedsFeedScope
@Subcomponent(modules = [BreedsFeedModule::class])
interface BreedsFeedComponent {
    fun inject(activity: BreedsActivity)
    fun inject(fragment: BreedsFragment)
    fun inject(breedsViewModel: BreedsViewModel)
}

interface BreedsFeedParentComponent {
    fun getBreedsFeedComponent(): BreedsFeedComponent
}

object BreedsFeedComponentHolder {

    private var component: BreedsFeedComponent? = null
    private var usageCounter = 0

    fun initComponent(): BreedsFeedComponent {
        if (component == null) {
            component = (ParentComponentProvider.appComponent as BreedsFeedParentComponent).getBreedsFeedComponent()
        }
        usageCounter++
        return component!!
    }

    fun getComponent() = component

    fun releaseComponent() {
        usageCounter--
        if (usageCounter <= 0) {
            usageCounter = 0
            component = null
        }
    }
}