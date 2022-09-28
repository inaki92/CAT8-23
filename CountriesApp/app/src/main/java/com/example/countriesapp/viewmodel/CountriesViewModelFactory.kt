package com.example.countriesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.rest.CountryRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CountriesViewModelFactory @Inject constructor(
    private val repository: CountryRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(repository, dispatcher) as T
    }
}