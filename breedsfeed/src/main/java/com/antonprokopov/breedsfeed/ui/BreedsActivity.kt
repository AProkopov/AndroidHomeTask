package com.antonprokopov.breedsfeed.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antonprokopov.breedsfeed.R
import com.antonprokopov.breedsfeed.di.BreedsFeedComponentHolder
import com.antonprokopov.core.ui.ActivityLifecycleOwnerHolder
import javax.inject.Inject

class BreedsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, BreedsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    @Inject
    lateinit var activityLifecycleOwnerHolder: ActivityLifecycleOwnerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        BreedsFeedComponentHolder.initComponent().inject(this)
        activityLifecycleOwnerHolder.init(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_breeds_feed)

        val fragment = supportFragmentManager.findFragmentByTag(BreedsFragment.TAG) ?: BreedsFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_feed_container, fragment, BreedsFragment.TAG)
        transaction.commit()
    }

    override fun onDestroy() {
        BreedsFeedComponentHolder.releaseComponent()
        super.onDestroy()
    }
}