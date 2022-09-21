package com.example.disneycodechallenge_generic.di

import android.app.Application

class UserApp : Application() {

    override fun onCreate() {
        super.onCreate()
        userComponent = DaggerUserComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var userComponent: UserComponent
    }
}
