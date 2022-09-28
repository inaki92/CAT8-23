package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class SZL(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null
)