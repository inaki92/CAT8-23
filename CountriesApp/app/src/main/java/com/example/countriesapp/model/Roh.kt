package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Roh(
    @SerializedName("common")
    val common: String? = null,
    @SerializedName("official")
    val official: String? = null
)