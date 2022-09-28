package com.example.countriesapp.rest

import com.example.countriesapp.model.domain.DomainCountry
import com.example.countriesapp.model.domain.mapToDomainCountries
import com.example.countriesapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CountryRepository {
    fun getAllCountries(): Flow<UIState>
}

class CountryRepositoryImpl @Inject constructor(
    private val serviceApi: CountriesApi
) : CountryRepository {

    override fun getAllCountries(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceApi.getCountries()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.mapToDomainCountries()))
                } ?: throw Exception("Countries body is null")
            } else {
                throw Exception("FAILURE RESPONSE")
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}