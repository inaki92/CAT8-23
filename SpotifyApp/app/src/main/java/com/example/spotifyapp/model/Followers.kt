package com.example.spotifyapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Followers(
    @Json(name = "href")
    val href: Any? = null,
    @Json(name = "total")
    val total: Int? = null
)