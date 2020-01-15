package me.aluceps.tamboon.presentation.charities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import me.aluceps.tamboon.R
import me.aluceps.tamboon.databinding.FragmentCharitiesBinding
import me.aluceps.tamboon.di.ViewModelFactory
import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.presentation.common.ViewNavigator
import javax.inject.Inject

class CharitiesFragment : DaggerFragment() {

    @Inject
    lateinit var viewNavigator: ViewNavigator
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
            (activity as CharitiesActivity).setupTitle(context.getString(R.string.title_charities))
        }
        setupRecycerView()
        viewModel.fetch()
    }

    override fun onStart() {
        super.onStart()
        viewModel.items.observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(scope())
                .subscribe {
                    listAdapter.update(it)
                }
    }

    private fun setupRecycerView() {
        listAdapter = CharityListAdapter()
        listAdapter.setOnClickListener(object : CharityListAdapter.OnClickListener {
            override fun click(item: Charity) {
                viewNavigator.navigateToDonationActivity(item)
            }
        })
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    companion object {
        fun newInstance() = CharitiesFragment()
    }
}