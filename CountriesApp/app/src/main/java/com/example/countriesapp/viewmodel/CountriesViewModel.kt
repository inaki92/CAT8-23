package com.example.countriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.rest.CountryRepository
import com.example.countriesapp.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesViewModel(
    private val repository: CountryRepository,
    private val ioDispatcher: CoroutineDispatcher
)  : ViewModel() {

    init {
        getAllCountries()
    }

    private val _countries: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val countries: LiveData<UIState> get() = _countries

    private fun getAllCountries() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                repository.getAllCountries().collect {
                    _countries.postValue(it)
                }
            }
        }
    }
}