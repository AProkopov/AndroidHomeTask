package com.antonprokopov.breedsfeed.ui

import android.content.Context
import com.antonprokopov.breedsfeedapi.route.BreedsFeedRouter

class BreedsFeedRouterImpl: BreedsFeedRouter {
    override fun openBreedsFeed(context: Context) {
        context.startActivity(BreedsActivity.newIntent(context))
    }
}