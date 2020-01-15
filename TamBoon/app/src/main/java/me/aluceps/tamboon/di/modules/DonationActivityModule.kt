package me.aluceps.tamboon.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.aluceps.tamboon.di.ViewModelKey
import me.aluceps.tamboon.presentation.donations.DonationActivity
import me.aluceps.tamboon.presentation.donations.DonationFragment
import me.aluceps.tamboon.presentation.donations.DonationViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(DonationViewModel::class)
    fun bindDonationViewModel(
            viewModel: DonationViewModel
    ): ViewModel
}