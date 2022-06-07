package com.pmesa48.moviedatabase.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pmesa48.moviedatabase.databinding.DiscoverItemViewBinding

class DiscoverAdapter(private val onClick: (movie: DiscoverMovieView) -> Unit) : ListAdapter<DiscoverMovieView, DiscoverViewHolder>(
    DiscoverMoviesDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = DiscoverItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class DiscoverMoviesDiffCallback: DiffUtil.ItemCallback<DiscoverMovieView>() {
        override fun areItemsTheSame(oldItem: DiscoverMovieView, newItem: DiscoverMovieView) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DiscoverMovieView, newItem: DiscoverMovieView) =
            oldItem == newItem

    }
}