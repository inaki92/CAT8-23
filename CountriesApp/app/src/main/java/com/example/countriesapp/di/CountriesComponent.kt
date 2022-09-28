package com.example.countriesapp.di

import com.example.countriesapp.MainActivity
import com.example.countriesapp.location.LocationService
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface CountriesComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(locationService: LocationService)
}