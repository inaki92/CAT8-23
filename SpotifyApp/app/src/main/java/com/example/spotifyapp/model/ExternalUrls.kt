package com.example.spotifyapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExternalUrls(
    @Json(name = "spotify")
    val spotify: String? = null
)