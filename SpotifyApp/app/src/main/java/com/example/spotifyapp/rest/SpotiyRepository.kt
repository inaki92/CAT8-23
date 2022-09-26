package com.example.spotifyapp.rest

import com.example.spotifyapp.model.NetworkArtist
import com.example.spotifyapp.model.authentication.AuthenticationToken
import com.example.spotifyapp.model.domain.mapToDomainArtist
import com.example.spotifyapp.model.search.NetworkSearch
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
    suspend fun getArtists(artistIds: List<String>): Response<NetworkArtist>
    suspend fun getArtistsByGenre(genre: String): Response<NetworkSearch>
    suspend fun authenticate(): Response<AuthenticationToken>
}

// DATA LAYER

class SpotifyRepositoryImpl @Inject constructor(
    @Named("spotifyService") private val spotifyServiceApi: SpotifyServiceApi,
    @Named("authService") private val authServiceApi: SpotifyServiceApi
) : SpotifyRepository {

    override suspend fun getArtists(artistIds: List<String>): Response<NetworkArtist> =
        spotifyServiceApi.getArtists(artistIds.joinToString(","))

    override suspend fun getArtistsByGenre(genre: String): Response<NetworkSearch> =
        spotifyServiceApi.getArtistsByGenre(genre, "artist")

    override suspend fun authenticate(): Response<AuthenticationToken> =
        authServiceApi.generateAuthToken()

}
