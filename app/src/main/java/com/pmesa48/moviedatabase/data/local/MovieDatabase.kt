package com.pmesa48.moviedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pmesa48.moviedatabase.domain.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun discoverDao(): DiscoverDao
}
