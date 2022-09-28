package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class CountryNetwork(
    @SerializedName("altSpellings")
    val altSpellings: List<String>? = listOf(),
    @SerializedName("area")
    val area: Double? = 0.0,
    @SerializedName("borders")
    val borders: List<String>? = listOf(),
    @SerializedName("capital")
    val capital: List<String>? = listOf(),
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo? = CapitalInfo(),
    @SerializedName("car")
    val car: Car? = Car(),
    @SerializedName("cca2")
    val cca2: String? = "",
    @SerializedName("cca3")
    val cca3: String? = "",
    @SerializedName("ccn3")
    val ccn3: String? = "",
    @SerializedName("cioc")
    val cioc: String? = "",
    @SerializedName("coatOfArms")
    val coatOfArms: CoatOfArms? = CoatOfArms(),
    @SerializedName("continents")
    val continents: List<String>? = listOf(),
    @SerializedName("currencies")
    val currencies: Currencies? = Currencies(),
    @SerializedName("demonyms")
    val demonyms: Demonyms? = Demonyms(),
    @SerializedName("fifa")
    val fifa: String? = "",
    @SerializedName("flag")
    val flag: String? = "",
    @SerializedName("flags")
    val flags: Flags? = Flags(),
    @SerializedName("gini")
    val gini: Gini? = Gini(),
    @SerializedName("idd")
    val idd: Idd? = Idd(),
    @SerializedName("independent")
    val independent: Boolean? = false,
    @SerializedName("landlocked")
    val landlocked: Boolean? = false,
    @SerializedName("languages")
    val languages: Languages? = Languages(),
    @SerializedName("latlng")
    val latlng: List<Double>? = listOf(),
    @SerializedName("maps")
    val maps: Maps? = Maps(),
    @SerializedName("name")
    val name: Name? = Name(),
    @SerializedName("population")
    val population: Int? = 0,
    @SerializedName("postalCode")
    val postalCode: PostalCode? = PostalCode(),
    @SerializedName("region")
    val region: String? = "",
    @SerializedName("startOfWeek")
    val startOfWeek: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("subregion")
    val subregion: String? = "",
    @SerializedName("timezones")
    val timezones: List<String>? = listOf(),
    @SerializedName("tld")
    val tld: List<String>? = listOf(),
    @SerializedName("translations")
    val translations: Translations? = Translations(),
    @SerializedName("unMember")
    val unMember: Boolean? = false
)