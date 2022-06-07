package com.pmesa48.moviedatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.pmesa48.moviedatabase.R
import com.pmesa48.moviedatabase.databinding.ActivityMainBinding
import com.pmesa48.moviedatabase.ui.discover.DiscoverFragment
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel.ViewModelState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var discoverFragment: DiscoverFragment
    private val discoverViewModel: DiscoverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        discoverFragment = DiscoverFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_fragment_container, discoverFragment)
            .commitNow()
        discoverViewModel.state.observe(this, this::onDiscoverStateChanged)
        binding.activityMainSwipeRefresh.setOnRefreshListener { discoverFragment.refresh() }
    }

    private fun onDiscoverStateChanged(state: DiscoverViewModel.ViewModelState) {
        when(state){
            is ShowDiscoverList -> binding.activityMainSwipeRefresh.isRefreshing = false
            is HideDiscoverList -> binding.activityMainSwipeRefresh.isRefreshing = false
            is ShowLoading -> binding.activityMainSwipeRefresh.isRefreshing = true
        }
    }
}