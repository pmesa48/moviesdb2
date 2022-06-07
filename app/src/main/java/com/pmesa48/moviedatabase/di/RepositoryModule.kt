package com.pmesa48.moviedatabase.di

import com.pmesa48.moviedatabase.data.local.DiscoverCache
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepository
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepositoryImpl
import com.pmesa48.moviedatabase.data.api.discover.DiscoverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesDiscoverRepository(
        discoverService: DiscoverService,
        discoverCache: DiscoverCache
    ) : DiscoverRepository {
        return DiscoverRepositoryImpl(discoverService, discoverCache)
    }
}