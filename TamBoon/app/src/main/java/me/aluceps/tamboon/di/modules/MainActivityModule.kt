package me.aluceps.tamboon.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.aluceps.tamboon.presentation.MainActivity
import me.aluceps.tamboon.presentation.charities.CharitiesFragment
import me.aluceps.tamboon.presentation.charities.CharitiesViewModel
import me.aluceps.tamboon.presentation.common.ViewModelKey

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

    @Binds
    @IntoMap
    @ViewModelKey(CharitiesViewModel::class)
    fun provideCharitiesViewModel(
            viewModel: CharitiesViewModel
    ): ViewModel
}