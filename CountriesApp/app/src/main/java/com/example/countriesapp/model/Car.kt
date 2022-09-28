package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("side")
    val side: String? = null,
    @SerializedName("signs")
    val signs: List<String?>? = null
)