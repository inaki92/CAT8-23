package com.example.spotifyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyapp.model.authentication.TokenHandling
import com.example.spotifyapp.rest.ResponseInterceptor
import com.example.spotifyapp.rest.SpotifyRepository
import com.example.spotifyapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotifyViewModel @Inject constructor(
    private val tokenHandling: TokenHandling,
    private val repository: SpotifyRepository,
    private val ioDispatcher: CoroutineDispatcher,
    private val responseInterceptor: ResponseInterceptor
) : ViewModel() {

    init {
        subscribeUnauthorisedError()
    }

    private fun subscribeUnauthorisedError() {
        viewModelScope.launch {
            responseInterceptor.unauthorizedError.collect {
                it?.let {
                    getAuthorizedToken()
                }
            }
        }
    }

    private fun getAuthorizedToken() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = repository.authenticate()
                if (response.isSuccessful) {
                    response.body()?.let {
                        tokenHandling.authToken = it.accessToken
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    private val _artists: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val artists: LiveData<UIState> get() = _artists

    fun getArtistsByIds(ids: List<String>) {
        viewModelScope.launch(ioDispatcher) {
            repository.getArtists(ids, this).collect {
                _artists.postValue(it)
            }
        }
    }


}