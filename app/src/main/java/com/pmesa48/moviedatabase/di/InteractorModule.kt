package com.pmesa48.moviedatabase.di

import com.pmesa48.moviedatabase.usecase.discover.DiscoverInteractor
import com.pmesa48.moviedatabase.usecase.discover.DiscoverInteractorImpl
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object InteractorModule {

    @Provides
    fun provideDiscoverInteractor(
        discoverRepository: DiscoverRepository
    ): DiscoverInteractor {
        return DiscoverInteractorImpl(discoverRepository)
    }
}