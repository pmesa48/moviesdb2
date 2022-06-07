package com.pmesa48.moviedatabase.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmesa48.moviedatabase.BuildConfig
import com.pmesa48.moviedatabase.ui.discover.DiscoverMovieView
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
) {
    fun toViewModel(): DiscoverMovieView {
        return DiscoverMovieView(
            id.toString(),
            title,
            BuildConfig.IMG_BASE_URL.plus(posterPath)
        )
    }
}