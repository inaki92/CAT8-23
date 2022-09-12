package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("image_url_small")
    val imageUrlSmall: String? = null
)