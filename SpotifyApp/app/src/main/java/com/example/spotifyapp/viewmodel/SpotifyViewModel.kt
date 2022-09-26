package com.example.spotifyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyapp.rest.ResponseInterceptor
import com.example.spotifyapp.usescases.AuthorizationUseCase
import com.example.spotifyapp.usescases.SearchArtistByGenreUseCase
import com.example.spotifyapp.usescases.SearchByArtistIdUseCase
import com.example.spotifyapp.utils.UIState
import com.example.spotifyapp.utils.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SpotifyViewModel"


// PRESENTATION LAYER

@HiltViewModel
class SpotifyViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val responseInterceptor: ResponseInterceptor,
    private val authorizationUseCase: AuthorizationUseCase,
    private val searchByArtistIdUseCase: SearchByArtistIdUseCase,
    private val searchByGenreIdUseCase: SearchArtistByGenreUseCase,
) : ViewModel() {

    init {
        subscribeUnauthorisedError()
    }

    private val _artists: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val artists: LiveData<UIState> get() = _artists

    private fun subscribeUnauthorisedError() {
        viewModelScope.launch(ioDispatcher) {
            responseInterceptor.unauthorizedError.collect {
                getAuthorizedToken()
            }
        }
    }

    private fun getAuthorizedToken() {
        viewModelScope.launch(ioDispatcher) {
            authorizationUseCase.execute(this)
        }
    }

    fun getIntentActionView(action: ViewIntent) {
        when (action) {
            is ViewIntent.SearchArtistsByIds -> {
                viewModelScope.launch(ioDispatcher) {
                    searchByArtistIdUseCase.execute(action.artists, this).collect {
                        _artists.postValue(it)
                    }
                }
            }
            is ViewIntent.SearchArtistsByGenre -> {
                viewModelScope.launch(ioDispatcher) {
                    searchByGenreIdUseCase.execute(action.genre.name.lowercase()).collect {
                        _artists.postValue(it)
                    }
                }
            }
        }
    }


}