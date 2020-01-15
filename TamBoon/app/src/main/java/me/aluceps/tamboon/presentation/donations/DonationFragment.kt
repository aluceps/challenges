package me.aluceps.tamboon.presentation.donations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.FragmentDonationBinding
import me.aluceps.tamboon.di.ViewModelFactory
import javax.inject.Inject

class DonationFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentDonationBinding

    private val viewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(DonationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        context?.let { context ->
            (activity as DonationActivity).setupTitle(context.resources.getString(R.string.title_donation))
        }
    }

    companion object {
        fun newInstance() = DonationFragment()
    }
}