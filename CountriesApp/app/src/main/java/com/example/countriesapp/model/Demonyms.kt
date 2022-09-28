package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Demonyms(
    @SerializedName("eng")
    val eng: Eng? = null,
    @SerializedName("fra")
    val fra: Fra? = null
)