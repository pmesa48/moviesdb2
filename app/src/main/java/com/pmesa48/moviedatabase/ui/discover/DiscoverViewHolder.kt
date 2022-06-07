package com.pmesa48.moviedatabase.ui.discover

import androidx.recyclerview.widget.RecyclerView
import com.pmesa48.moviedatabase.R
import com.pmesa48.moviedatabase.databinding.DiscoverItemViewBinding
import com.squareup.picasso.Picasso

class DiscoverViewHolder(private val binding: DiscoverItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(discoverMovieView: DiscoverMovieView, onClick: (movie: DiscoverMovieView) -> Unit) {
        binding.discoverItemViewTitle.text = discoverMovieView.title
        binding.discoverItemImgPoster.setOnClickListener { onClick(discoverMovieView) }
        Picasso.get()
            .load(discoverMovieView.posterPath)
            .resizeDimen(R.dimen.poster_width, R.dimen.poster_height)
            .into(binding.discoverItemImgPoster)
    }

}
