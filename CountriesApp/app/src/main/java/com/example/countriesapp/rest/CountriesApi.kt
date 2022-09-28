package com.example.countriesapp.rest

import com.example.countriesapp.model.CountryNetwork
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET(ALL_PATH)
    suspend fun getCountries(): Response<List<CountryNetwork>>

    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/"
        private const val ALL_PATH = "all"
    }
}