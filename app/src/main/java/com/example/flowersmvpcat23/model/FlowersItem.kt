package com.example.flowersmvpcat23.model


import com.google.gson.annotations.SerializedName

data class FlowersItem(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("instructions")
    val instructions: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("productId")
    val productId: Int? = null
)