package com.example.fruitsappmvvm.di

import com.example.fruitsappmvvm.rest.FruitsRepository
import com.example.fruitsappmvvm.rest.FruitsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesFruitRepository(
        repositoryImpl: FruitsRepositoryImpl
    ): FruitsRepository
}