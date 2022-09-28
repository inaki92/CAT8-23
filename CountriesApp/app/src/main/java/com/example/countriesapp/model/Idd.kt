package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Idd(
    @SerializedName("root")
    val root: String? = null,
    @SerializedName("suffixes")
    val suffixes: List<String?>? = null
)