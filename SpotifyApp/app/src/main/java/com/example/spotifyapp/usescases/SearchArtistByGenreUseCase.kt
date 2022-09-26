package com.example.spotifyapp.usescases

import com.example.spotifyapp.model.domain.mapToDomainArtist
import com.example.spotifyapp.model.domain.mapToDomainArtistSearch
import com.example.spotifyapp.rest.SpotifyRepository
import com.example.spotifyapp.utils.FailureResponse
import com.example.spotifyapp.utils.NullBodyResponse
import com.example.spotifyapp.utils.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchArtistByGenreUseCase @Inject constructor(
    private val repository: SpotifyRepository
) {

    fun execute(genre: String): Flow<UIState> = flow {
        emit(UIState.LOADING)

        // TODO fix network call being made before the token call
        delay(5000)

        try {
            val response = repository.getArtistsByGenre(genre)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.albums?.items.mapToDomainArtistSearch()))
                } ?: throw NullBodyResponse("Artists response is null")
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}