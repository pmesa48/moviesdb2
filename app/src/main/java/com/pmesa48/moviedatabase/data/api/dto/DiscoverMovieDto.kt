package com.pmesa48.moviedatabase.data.api.dto

import com.google.gson.annotations.SerializedName
import com.pmesa48.moviedatabase.domain.model.Movie

class DiscoverMovieDto {
    @SerializedName("id")
    val id: Long? = null

    @SerializedName("adult")
    val adult: Boolean = false

    @SerializedName("backdrop_path")
    val backDropPath: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null

    fun toDomainModel(): Movie {
        return Movie(
            id ?: 0,
            originalTitle ?: "",
            posterPath ?: ""
        )
    }
}
