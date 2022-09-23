package com.example.spotifyapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkArtist(
    @Json(name = "artists")
    val artists: List<Artist>? = null
)