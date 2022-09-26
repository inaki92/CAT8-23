package com.example.spotifyapp.model.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSearch(
    @Json(name = "albums")
    val albums: Albums? = Albums()
)