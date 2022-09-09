package com.example.fruitsappmvpcat23.di

import android.app.Application

class FruitsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        fruitsComponent = DaggerFruitsComponent
            .builder()
            .applicationModule(
                ApplicationModule(this)
            )
            .build()
    }

    companion object {
        lateinit var fruitsComponent: FruitsComponent
    }
}