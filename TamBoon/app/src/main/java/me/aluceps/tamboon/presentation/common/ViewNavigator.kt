package me.aluceps.tamboon.presentation.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.aluceps.tamboon.R
import me.aluceps.tamboon.presentation.charities.CharitiesFragment
import javax.inject.Inject

class ViewNavigator @Inject constructor(private val appCompatActivity: AppCompatActivity) {
    private val containerId = R.id.container
    private val fragmentManager get() = appCompatActivity.supportFragmentManager

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit()
    }

    fun navigateToCharities() {
        replaceFragment(CharitiesFragment.newInstance())
    }
}