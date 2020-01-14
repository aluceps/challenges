package me.aluceps.tamboon.presentation

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.aluceps.tamboon.BuildConfig
import me.aluceps.tamboon.di.DaggerAppComponent
import timber.log.Timber

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}