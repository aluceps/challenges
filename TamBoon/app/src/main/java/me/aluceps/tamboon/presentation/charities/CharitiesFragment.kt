package me.aluceps.tamboon.presentation.charities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.FragmentCharitiesBinding
import me.aluceps.tamboon.presentation.MainActivity
import me.aluceps.tamboon.presentation.common.ViewModelFactory
import javax.inject.Inject

class CharitiesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentCharitiesBinding

    private val viewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(CharitiesViewModel::class.java)
    }

    private lateinit var listAdapter: CharityListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            (activity as MainActivity).setupTitle(context.getString(R.string.title_charities))
        }
        binding.viewModel = viewModel
        setupRecycerView()
    }

    private fun setupRecycerView() {
        listAdapter = CharityListAdapter()
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        listAdapter.update(viewModel.items)
    }

    companion object {
        fun newInstance() = CharitiesFragment()
    }
}