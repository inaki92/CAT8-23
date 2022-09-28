package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class BYN(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null
)