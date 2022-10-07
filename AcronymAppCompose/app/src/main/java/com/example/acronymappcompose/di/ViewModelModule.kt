package com.example.acronymappcompose.di

import android.content.Context
import androidx.work.WorkManager
import com.example.acronymappcompose.usecase.AcronymDataUseCase
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
    fun providesWorkManager(context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    fun providesUseCase(workManager: WorkManager): AcronymDataUseCase =
        AcronymDataUseCase(workManager)
}