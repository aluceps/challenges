package me.aluceps.tamboon.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.aluceps.tamboon.di.ViewModelKey
import me.aluceps.tamboon.presentation.charities.CharitiesActivity
import me.aluceps.tamboon.presentation.charities.CharitiesFragment
import me.aluceps.tamboon.presentation.charities.CharitiesViewModel

@Module
interface CharitiesActivityBuilder {
    @ContributesAndroidInjector(modules = [
        CharitiesActivityModule::class
    ])

    fun contributeMainActivity(): CharitiesActivity
}

@Module
interface CharitiesActivityModule {
    @Binds
    fun providesAppCompatActivity(activity: CharitiesActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeCharitiesFragment(): CharitiesFragment

    @Binds
    @IntoMap
    @ViewModelKey(CharitiesViewModel::class)
    fun bindCharitiesViewModel(
            viewModel: CharitiesViewModel
    ): ViewModel
}