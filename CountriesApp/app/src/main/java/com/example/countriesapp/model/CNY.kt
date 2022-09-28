package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class CNY(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null
)