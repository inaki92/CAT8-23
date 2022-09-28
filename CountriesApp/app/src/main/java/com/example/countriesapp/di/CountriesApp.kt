package com.example.countriesapp.di

import android.app.Application

class CountriesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        countriesComponent = DaggerCountriesComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var countriesComponent: CountriesComponent
    }
}