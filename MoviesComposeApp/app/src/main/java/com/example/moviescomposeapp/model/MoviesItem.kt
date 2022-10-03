package com.example.moviescomposeapp.model


import com.google.gson.annotations.SerializedName

data class MoviesItem(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("name")
    val name: String? = null
)