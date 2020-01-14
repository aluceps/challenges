package me.aluceps.tamboon.di.modules

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.aluceps.tamboon.presentation.MainActivity
import me.aluceps.tamboon.presentation.charities.CharitiesFragment

@Module
interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])

    fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityModule {
    @Binds
    fun providesAppCompatActivity(activity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeCharitiesFragment(): CharitiesFragment
}