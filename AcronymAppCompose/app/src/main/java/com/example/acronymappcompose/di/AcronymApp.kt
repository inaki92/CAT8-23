package com.example.acronymappcompose.di

import android.app.Application

class AcronymApp  :  Application() {

    override fun onCreate() {
        super.onCreate()

        acronymComponent = DaggerAcronymComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var acronymComponent: AcronymComponent
    }
}