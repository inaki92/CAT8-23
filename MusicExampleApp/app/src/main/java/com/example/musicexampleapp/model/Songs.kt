package com.example.musicexampleapp.model


import com.google.gson.annotations.SerializedName

data class Songs(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val songs: List<Song>? = null
)