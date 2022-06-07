package com.pmesa48.moviedatabase.data.api.discover

import com.google.gson.annotations.SerializedName
import com.pmesa48.moviedatabase.data.api.dto.DiscoverMovieDto

class DiscoverServiceResponse {
    @SerializedName("results")
    val results: List<DiscoverMovieDto> = emptyList()
}