package com.wattpad.ca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wattpad.ca.ui.StoryDetailActivity
import com.wattpad.ca.adapters.WattpadListAdapter
import com.wattpad.ca.databinding.FragmentWattpadListBinding
import com.wattpad.ca.model.Story
import com.wattpad.ca.repository.WattpadRepository
import com.wattpad.ca.viewmodel.WattpadFavoritesViewModel
import com.wattpad.ca.viewmodel.WattpadViewModelFactory

class WattpadFavoritesFragment : Fragment() {

    lateinit var binding: FragmentWattpadListBinding

    private val viewModel: WattpadFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            WattpadViewModelFactory(
                WattpadRepository(requireContext())
            )
        ).get(WattpadFavoritesViewModel::class.java)
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
        binding.searchView.visibility = View.GONE

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        /*binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )*/

        viewModel.favoriteStories.observe(viewLifecycleOwner, Observer { items ->
            binding.loading.visibility = View.GONE
            binding.recyclerView.adapter = WattpadListAdapter(items, this::openDetail)
        })
    }

    private fun openDetail(story: Story) {
        StoryDetailActivity.open(requireContext(), story)
    }
}