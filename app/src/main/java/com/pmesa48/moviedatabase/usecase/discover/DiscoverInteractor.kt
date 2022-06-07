package com.pmesa48.moviedatabase.usecase.discover

import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverInteractor {
    fun discover(): Flow<List<Movie>>
}
