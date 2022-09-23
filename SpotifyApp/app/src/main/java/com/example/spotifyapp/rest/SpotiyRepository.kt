package com.example.spotifyapp.rest

import com.example.spotifyapp.model.authentication.AuthenticationToken
import com.example.spotifyapp.model.domain.mapToDomainArtist
import com.example.spotifyapp.utils.FailureResponse
import com.example.spotifyapp.utils.NullBodyResponse
import com.example.spotifyapp.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

interface SpotifyRepository {
    suspend fun getArtists(artistIds: List<String>, coroutineScope: CoroutineScope): StateFlow<UIState>
    suspend fun authenticate(): Response<AuthenticationToken>
}

class SpotifyRepositoryImpl @Inject constructor(
    @Named("spotifyService") private val spotifyServiceApi: SpotifyServiceApi,
    @Named("authService") private val authServiceApi: SpotifyServiceApi
) : SpotifyRepository {

    override suspend fun getArtists(
        artistIds: List<String>,
        coroutineScope: CoroutineScope
    ): StateFlow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = spotifyServiceApi.getArtists(artistIds.joinToString { "," })
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

    override suspend fun authenticate(): Response<AuthenticationToken> =
        authServiceApi.generateAuthToken()

}
