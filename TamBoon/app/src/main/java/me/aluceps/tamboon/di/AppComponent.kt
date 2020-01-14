package me.aluceps.tamboon.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.aluceps.tamboon.di.modules.MainActivityBuilder
import me.aluceps.tamboon.presentation.App

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    MainActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    override fun inject(instance: App?)
}