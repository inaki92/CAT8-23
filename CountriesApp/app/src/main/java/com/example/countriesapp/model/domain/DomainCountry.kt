package com.example.countriesapp.model.domain

import com.example.countriesapp.model.CountryNetwork

data class DomainCountry(
    val name: String,
    val flag: String,
    val capital: String,
    val population: Int
)

fun List<CountryNetwork>.mapToDomainCountries(): List<DomainCountry> =
    this.map {
        DomainCountry(
            name = it.name?.official ?: "",
            flag = it.flags?.png ?: "",
            capital = it.capital?.firstOrNull() ?: "",
            population = it.population ?: 0
        )
    }
