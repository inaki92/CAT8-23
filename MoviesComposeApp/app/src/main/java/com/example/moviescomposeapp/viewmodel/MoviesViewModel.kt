package com.example.moviescomposeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescomposeapp.rest.MoviesRepository
import com.example.moviescomposeapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getMovies()
    }

    private val _movies: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val movies: LiveData<UIState> get() = _movies

    private fun getMovies() {
        viewModelScope.launch(ioDispatcher) {
            repository.getMovies().collect {
                _movies.postValue(it)
            }
        }
    }
}