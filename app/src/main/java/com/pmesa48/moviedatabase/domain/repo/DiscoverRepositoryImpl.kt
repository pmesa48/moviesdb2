package com.pmesa48.moviedatabase.domain.repo

import com.pmesa48.moviedatabase.domain.model.Movie
import com.pmesa48.moviedatabase.data.api.discover.DiscoverService
import com.pmesa48.moviedatabase.data.local.DiscoverCache
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val api: DiscoverService,
    private val cache: DiscoverCache
) : DiscoverRepository {

    companion object {
        const val PAGE_SIZE = 20
        const val PAGE_THRESHOLD = 10
    }
    override fun discover(): Flow<List<Movie>> = cache.fetch()

    suspend fun checkRequireNewPage(lastVisible: Int) {
        val size = cache.size() / lastVisible + 1
        if(lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = api.fetch(page)
            cache.insertAll(newMovies)
        }
    }

}