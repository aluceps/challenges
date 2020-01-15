package me.aluceps.tamboon.di.modules

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.aluceps.tamboon.presentation.donations.DonationActivity
import me.aluceps.tamboon.presentation.donations.DonationFragment

@Module
interface DonationActivityBuilder {
    @ContributesAndroidInjector(modules = [
        DonationActivityModule::class
    ])

    fun contributeDonationActivity(): DonationActivity
}

@Module
interface DonationActivityModule {
    @Binds
    fun provideAppCompatActivity(activity: DonationActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeDonationFragment(): DonationFragment
}