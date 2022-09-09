package com.example.fruitsappmvpcat23.di

import com.example.fruitsappmvpcat23.database.LocalFruitsRepository
import com.example.fruitsappmvpcat23.database.LocalFruitsRepositoryImpl
import com.example.fruitsappmvpcat23.rest.FruitsRepository
import com.example.fruitsappmvpcat23.rest.FruitsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun providesLocalFruitsRepo(
        localFruitsRepositoryImpl: LocalFruitsRepositoryImpl
    ): LocalFruitsRepository

    @Binds
    abstract fun providesFruitsNetworkRepo(
        fruitsRepositoryImpl: FruitsRepositoryImpl
    ): FruitsRepository

}