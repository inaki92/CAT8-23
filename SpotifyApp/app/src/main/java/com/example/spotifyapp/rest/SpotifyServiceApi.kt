package com.example.spotifyapp.rest

import com.example.spotifyapp.model.NetworkArtist
import com.example.spotifyapp.model.authentication.AuthenticationToken
import retrofit2.Response
import retrofit2.http.*

interface SpotifyServiceApi {

    @FormUrlEncoded
    @POST(AUTH_PATH)
    suspend fun generateAuthToken(
        @Field("grant_type") type: String = "client_credentials"
    ): Response<AuthenticationToken>


    @GET(ARTISTS_PATH)
    suspend fun getArtists(
        @Query("ids") artistIds: String
    ): Response<NetworkArtist>


    companion object {
        //  https://api.spotify.com/v1/artists?ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6
        // https://accounts.spotify.com/api/token

        const val AUTH_BASE_URL = "https://accounts.spotify.com/api/"
        const val SERVICE_BASE_URL = "https://api.spotify.com/v1/"

        private const val AUTH_PATH = "token"
        private const val ARTISTS_PATH = "artists"
    }

}