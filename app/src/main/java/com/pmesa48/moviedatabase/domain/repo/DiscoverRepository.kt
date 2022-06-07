package com.pmesa48.moviedatabase.domain.repo

import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {

    fun discover() : Flow<List<Movie>>

}
