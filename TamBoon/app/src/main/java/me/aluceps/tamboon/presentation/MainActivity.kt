package me.aluceps.tamboon.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ActivityMainBinding
import me.aluceps.tamboon.presentation.common.BaseActivity
import me.aluceps.tamboon.presentation.common.ViewNavigator
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewNavigator: ViewNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewNavigator.navigateToCharities()
    }

    fun setupTitle(title: String) {
        this.title = title
    }
}
