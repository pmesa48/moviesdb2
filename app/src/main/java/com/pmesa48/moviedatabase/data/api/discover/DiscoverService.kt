package com.pmesa48.moviedatabase.data.api.discover

import com.pmesa48.moviedatabase.domain.model.Movie


interface DiscoverService {

    suspend fun fetch(page: Int): List<Movie>
}
