package com.pmesa48.moviedatabase.data.local

import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverCacheRoom @Inject constructor(
    private val discoverDao: DiscoverDao
    ) : DiscoverCache {
    override fun fetch(): Flow<List<Movie>> {
        return discoverDao.getAll()
    }

    override fun insertAll(movies: List<Movie>) {
        discoverDao.insertAll(movies)
    }

    override fun size(): Int {
        return discoverDao.size()
    }
}