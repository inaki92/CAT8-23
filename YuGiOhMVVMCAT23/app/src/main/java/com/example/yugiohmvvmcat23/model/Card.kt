package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("data")
    val cards: List<Data?>? = null
)