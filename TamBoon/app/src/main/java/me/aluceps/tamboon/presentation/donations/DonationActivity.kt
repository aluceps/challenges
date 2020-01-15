package me.aluceps.tamboon.presentation.donations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ActivityDonationBinding
import me.aluceps.tamboon.di.ViewModelFactory
import me.aluceps.tamboon.presentation.common.BaseActivity
import me.aluceps.tamboon.presentation.common.ViewNavigator
import javax.inject.Inject

class DonationActivity : BaseActivity() {

    @Inject
    lateinit var viewNavigator: ViewNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDonationBinding>(this, R.layout.activity_donation)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DonationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewNavigator.navigateToDonation()
    }

    fun setupTitle(title: String) {
        this.title = title
    }

    companion object {
        private fun createIntent(context: Context) =
                Intent(context, DonationActivity::class.java)

        fun start(context: Context) {
            createIntent(context).let {
                context.startActivity(it)
            }
        }
    }
}