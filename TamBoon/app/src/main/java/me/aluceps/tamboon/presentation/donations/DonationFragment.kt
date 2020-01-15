package me.aluceps.tamboon.presentation.donations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.FragmentDonationBinding

class DonationFragment : DaggerFragment() {

    private lateinit var binding: FragmentDonationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            (activity as DonationActivity).setupTitle(context.resources.getString(R.string.title_donation))
        }
    }

    companion object {
        fun newInstance() = DonationFragment()
    }
}