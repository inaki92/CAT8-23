package com.example.acronymappcompose.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import com.example.acronymappcompose.utils.WorkerFactoryImpl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideContext(): Context = application.applicationContext
}