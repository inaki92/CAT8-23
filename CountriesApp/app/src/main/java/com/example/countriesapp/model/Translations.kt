package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Translations(
    @SerializedName("ara")
    val ara: Ara? = Ara(),
    @SerializedName("bre")
    val bre: Bre? = Bre()
)