package me.aluceps.tamboon.presentation.charities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ActivityCharitiesBinding
import me.aluceps.tamboon.di.ViewModelFactory
import me.aluceps.tamboon.presentation.common.BaseActivity
import me.aluceps.tamboon.presentation.common.ViewNavigator
import javax.inject.Inject

class CharitiesActivity : BaseActivity() {

    @Inject
    lateinit var viewNavigator: ViewNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityCharitiesBinding>(this, R.layout.activity_charities)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CharitiesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewNavigator.navigateToCharities()
    }

    fun setupTitle(title: String) {
        this.title = title
    }
}
