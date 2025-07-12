package com.jonas.cookly

import android.app.Application
import com.jonas.cookly.core.util.logging.DebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
