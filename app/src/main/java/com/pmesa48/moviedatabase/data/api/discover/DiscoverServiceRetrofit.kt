package com.pmesa48.moviedatabase.data.api.discover

import com.pmesa48.moviedatabase.domain.model.Movie
import retrofit2.Retrofit
import javax.inject.Inject

class DiscoverServiceRetrofit @Inject constructor(
    retrofit: Retrofit
) : DiscoverService {

    private var api: DiscoverServiceApi = retrofit.create(DiscoverServiceApi::class.java)

    override suspend fun fetch(page: Int): List<Movie> =
        api.getVideoList(page).results.map { it.toDomainModel() }
}