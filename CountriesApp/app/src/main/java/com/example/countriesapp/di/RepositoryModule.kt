package com.example.countriesapp.di

import com.example.countriesapp.rest.CountryRepository
import com.example.countriesapp.rest.CountryRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        repositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}