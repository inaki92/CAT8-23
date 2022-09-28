package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Lit(
    @SerializedName("common")
    val common: String? = null,
    @SerializedName("official")
    val official: String? = null
)