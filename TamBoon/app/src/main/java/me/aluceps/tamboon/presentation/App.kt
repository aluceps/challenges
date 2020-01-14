package me.aluceps.tamboon.presentation

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.aluceps.tamboon.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .build()
}