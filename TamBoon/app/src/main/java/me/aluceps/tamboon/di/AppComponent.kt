package me.aluceps.tamboon.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.aluceps.tamboon.di.modules.CharitiesActivityBuilder
import me.aluceps.tamboon.di.modules.DonationActivityBuilder
import me.aluceps.tamboon.presentation.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    CharitiesActivityBuilder::class,
    DonationActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    override fun inject(instance: App?)
}