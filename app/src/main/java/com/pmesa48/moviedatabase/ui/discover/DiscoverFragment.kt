package com.pmesa48.moviedatabase.ui.discover

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pmesa48.moviedatabase.databinding.FragmentDiscoverBinding
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel.ViewModelState
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel.ViewModelState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  DiscoverFragment : Fragment() {

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    private lateinit var adapter: DiscoverAdapter
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DiscoverViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiscoverAdapter {
            Toast.makeText(context, it.title, Toast.LENGTH_LONG).show()
        }
        binding.discoverRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.discoverRecyclerView.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner) { state ->
            onNewState(state)
        }
        viewModel.discoverMovies()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onNewState(state: ViewModelState) {
        when(state){
            is ShowDiscoverList -> updateDiscoverList(state.movieList)
            is HideDiscoverList -> hideDiscoverList()
            is ShowLoading -> showLoading()
        }
    }

    private fun showLoading() {
        binding.discoverProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.discoverProgressBar.visibility = View.GONE
    }

    private fun hideDiscoverList() {
        hideLoading()
        binding.discoverRecyclerView.visibility = View.GONE
        binding.discoverFragmentTitle.visibility = View.GONE
    }

    private fun updateDiscoverList(newList: List<DiscoverMovieView>) {
        hideLoading()
        binding.discoverRecyclerView.visibility = View.VISIBLE
        binding.discoverFragmentTitle.visibility = View.VISIBLE
        adapter.submitList(newList)
    }

    fun refresh() {
        viewModel.discoverMovies()
    }
}