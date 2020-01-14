package me.aluceps.tamboon.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import me.aluceps.tamboon.presentation.common.ViewModelFactory

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
