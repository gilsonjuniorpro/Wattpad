package com.wattpad.ca.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wattpad.ca.R
import com.wattpad.ca.adapters.WattpadListAdapter
import com.wattpad.ca.databinding.FragmentWattpadListBinding
import com.wattpad.ca.model.Story
import com.wattpad.ca.ui.StoryDetailActivity
import com.wattpad.ca.viewmodel.WattpadListViewModel


class WattpadListFragment : Fragment() {

    lateinit var binding: FragmentWattpadListBinding

    private val viewModel: WattpadListViewModel by lazy {
        ViewModelProvider(this).get(WattpadListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        /*binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )*/

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is WattpadListViewModel.State.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }

                is WattpadListViewModel.State.Loaded -> {
                    binding.loading.visibility = View.GONE
                    binding.recyclerView.adapter =
                        WattpadListAdapter(state.items, this::openDetail)
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
}