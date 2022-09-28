package com.example.graphqlspaceexample.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SpaceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SpaceApp)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
    }
}