package me.aluceps.tamboon.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.ActivityMainBinding
import me.aluceps.tamboon.presentation.common.BaseActivity

class MainActivity : BaseActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.root
    }
}
