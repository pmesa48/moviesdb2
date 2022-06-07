package com.pmesa48.moviedatabase.usecase.discover


import com.pmesa48.moviedatabase.domain.repo.DiscoverRepository
import javax.inject.Inject

class DiscoverInteractorImpl @Inject constructor(
    private val repository: DiscoverRepository
) : DiscoverInteractor {

    override fun discover() =
        repository.discover()

}
