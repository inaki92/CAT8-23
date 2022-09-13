package com.example.yugiohmvvmcat23.di

import android.app.Application

class YuGiOhApp : Application() {

    override fun onCreate() {
        super.onCreate()
        yuGiOhComponent = DaggerYuGiOhComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var yuGiOhComponent: YuGiOhComponent
    }
}