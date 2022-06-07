package com.pmesa48.moviedatabase.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmesa48.moviedatabase.usecase.discover.DiscoverInteractor
import com.pmesa48.moviedatabase.ui.discover.DiscoverViewModel.ViewModelState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoverInteractor : DiscoverInteractor
) : ViewModel() {

    var state : MutableLiveData<ViewModelState> = MutableLiveData()

    private fun setState(newState: ViewModelState) {
        state.value = newState
    }

    fun discoverMovies() {
        viewModelScope.launch {
            setState(HideDiscoverList)
            setState(ShowLoading)
            discoverInteractor.discover().collect { result ->
                setState(ShowDiscoverList(result.map { it.toViewModel() }))
            }

        }
    }

    sealed class ViewModelState {
        data class ShowDiscoverList(val movieList: List<DiscoverMovieView>) : ViewModelState()
        object HideDiscoverList : ViewModelState()
        object ShowLoading : ViewModelState()
    }
}