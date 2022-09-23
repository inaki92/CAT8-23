package com.example.spotifyapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: Int? = null
)