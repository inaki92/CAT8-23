package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("png")
    val png: String? = null,
    @SerializedName("svg")
    val svg: String? = null
)