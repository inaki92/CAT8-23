package com.example.countriesapp.di

import com.example.countriesapp.rest.CountryRepository
import com.example.countriesapp.viewmodel.CountriesViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ViewModelModule {

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideViewModelFactory(
        repository: CountryRepository,
        ioDispatcher: CoroutineDispatcher
    ): CountriesViewModelFactory =
        CountriesViewModelFactory(repository, ioDispatcher)
}