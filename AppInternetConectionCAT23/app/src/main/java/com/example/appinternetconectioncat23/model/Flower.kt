package com.example.appinternetconectioncat23.model

import com.google.gson.annotations.SerializedName

data class Flower(
    @SerializedName("category")
    val category: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("name")
    val flowerName: String,
    @SerializedName("productId")
    val id: String
)