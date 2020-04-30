package com.wattpad.ca.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wattpad.ca.R
import com.wattpad.ca.adapters.WattpadListAdapter
import com.wattpad.ca.databinding.FragmentWattpadListBinding
import com.wattpad.ca.model.Story
import com.wattpad.ca.ui.StoryDetailActivity
import com.wattpad.ca.viewmodel.WattpadListViewModel


class WattpadListFragment : Fragment() {

    lateinit var binding: FragmentWattpadListBinding
    lateinit var layoutManager:LinearLayoutManager

    private val viewModel: WattpadListViewModel by lazy {
        ViewModelProvider(this).get(WattpadListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWattpadListBinding.inflate(inflater, container, false)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is WattpadListViewModel.State.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }

                is WattpadListViewModel.State.Loaded -> {
                    binding.loading.visibility = View.GONE
                    binding.recyclerView.adapter = WattpadListAdapter(state.items, this::openDetail)

                    binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isLoading = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)

                            var currentItems = layoutManager.childCount
                            var totalItems = layoutManager.itemCount
                            var scrollOutItems = layoutManager.findFirstVisibleItemPosition()

                            if(isLoading && (currentItems + scrollOutItems == totalItems)){
                                viewModel.loadMoreStories()
                            }
                        }
                    })
                }

                is WattpadListViewModel.State.Error -> {
                    binding.loading.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        })
        viewModel.loadStories()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    //viewModel.search()
                    viewModel.searchRetrofit()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun openDetail(story: Story) {
        StoryDetailActivity.open(requireContext(), story)
    }

    companion object {
        var isLoading = false

        fun setIsLoading(value: Boolean) {
            isLoading = value
        }
    }
}