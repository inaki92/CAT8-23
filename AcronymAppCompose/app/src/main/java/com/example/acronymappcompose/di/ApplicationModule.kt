package com.example.acronymappcompose.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideContext(): Context = application.applicationContext
}