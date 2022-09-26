package com.example.spotifyapp.model.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Albums(
    @Json(name = "href")
    val href: String? = "",
    @Json(name = "items")
    val items: List<Item>? = listOf(),
    @Json(name = "limit")
    val limit: Int? = 0,
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "offset")
    val offset: Int? = 0,
    @Json(name = "previous")
    val previous: Any? = Any(),
    @Json(name = "total")
    val total: Int? = 0
)