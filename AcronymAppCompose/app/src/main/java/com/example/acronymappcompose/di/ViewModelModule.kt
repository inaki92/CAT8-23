package com.example.acronymappcompose.di

import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import com.example.acronymappcompose.utils.WorkerFactoryImpl
import com.example.acronymappcompose.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        useCase: AcronymDataUseCase,
        ioDispatcher: CoroutineDispatcher
    ): ViewModelFactory =
        ViewModelFactory(useCase, ioDispatcher)

    @Provides
    fun providesWorkManagerFactory(
        repositoryModule: AcronymRepository,
        useCase: AcronymDataUseCase
    ): WorkerFactory =
        WorkerFactoryImpl(repositoryModule, useCase)

    @Provides
    fun providesWorkManager(context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    fun providesUseCase(workManager: WorkManager): AcronymDataUseCase =
        AcronymDataUseCase(workManager)
}