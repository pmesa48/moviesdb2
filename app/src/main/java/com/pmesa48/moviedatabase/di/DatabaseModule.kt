package com.pmesa48.moviedatabase.di

import android.content.Context
import androidx.room.Room
import com.pmesa48.moviedatabase.data.local.DiscoverDao
import com.pmesa48.moviedatabase.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDiscoverDao(database: MovieDatabase): DiscoverDao {
        return database.discoverDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            "movies.db"
        ).build()
    }
}