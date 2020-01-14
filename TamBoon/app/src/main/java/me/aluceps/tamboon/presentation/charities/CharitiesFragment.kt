package me.aluceps.tamboon.presentation.charities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.FragmentCharitiesBinding
import me.aluceps.tamboon.presentation.MainActivity

class CharitiesFragment : DaggerFragment() {

    private lateinit var binding: FragmentCharitiesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            (activity as MainActivity).setupTitle(context.getString(R.string.title_charities))
        }
    }

    companion object {
        fun newInstance() = CharitiesFragment()
    }
}