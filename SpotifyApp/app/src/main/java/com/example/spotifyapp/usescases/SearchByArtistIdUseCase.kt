package com.example.spotifyapp.usescases

import com.example.spotifyapp.model.domain.mapToDomainArtist
import com.example.spotifyapp.rest.SpotifyRepository
import com.example.spotifyapp.utils.FailureResponse
import com.example.spotifyapp.utils.NullBodyResponse
import com.example.spotifyapp.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchByArtistIdUseCase @Inject  constructor(
    private val repository: SpotifyRepository
) {

    fun execute(artistIds: List<String>, coroutineScope: CoroutineScope): StateFlow<UIState> = flow {
        emit(UIState.LOADING)

        // TODO fix network call being made before the token call
        kotlinx.coroutines.delay(5000)

        try {
            val response = repository.getArtists(artistIds)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.artists.mapToDomainArtist()))
                } ?: throw NullBodyResponse("Artists response is null")
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }.stateIn(
        coroutineScope,
        SharingStarted.Eagerly,
        UIState.LOADING
    )
}