package com.example.spotifyapp.usescases

import android.util.Log
import com.example.spotifyapp.model.authentication.TokenHandling
import com.example.spotifyapp.rest.SpotifyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AuthorizationUseCase"

// DOMAIN LAYER
class AuthorizationUseCase @Inject constructor(
    private val tokenHandling: TokenHandling,
    private val repository: SpotifyRepository
) {

    fun execute(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            try {
                val response = repository.authenticate()
                if (response.isSuccessful) {
                    response.body()?.let {
                        tokenHandling.authToken = it.accessToken
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "getAuthorizedToken: ${e.localizedMessage}", e)
            }
        }
    }
}