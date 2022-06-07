package com.pmesa48.moviedatabase.data.api.discover

import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverServiceApi {

    @GET("/3/discover/movie")
    suspend fun getVideoList(@Query("page") page: Int): DiscoverServiceResponse
}