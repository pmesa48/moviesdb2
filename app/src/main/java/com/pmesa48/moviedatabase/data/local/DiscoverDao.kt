package com.pmesa48.moviedatabase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscoverDao {
    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAll(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<Movie>)

    @Query("SELECT COUNT(id) FROM movies")
    fun size(): Int
}
