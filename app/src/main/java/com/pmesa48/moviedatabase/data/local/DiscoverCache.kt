package com.pmesa48.moviedatabase.data.local

import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverCache {
    fun fetch(): Flow<List<Movie>>
    fun insertAll(movies: List<Movie>)
    fun size(): Int
}
