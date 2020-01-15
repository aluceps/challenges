package me.aluceps.tamboon.presentation.donations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ActivityDonationBinding
import me.aluceps.tamboon.presentation.common.BaseActivity
import me.aluceps.tamboon.presentation.common.ViewNavigator
import javax.inject.Inject

class DonationActivity : BaseActivity() {

    @Inject
    lateinit var viewNavigator: ViewNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityDonationBinding>(this, R.layout.activity_donation)
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