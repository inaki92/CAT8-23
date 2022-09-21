package com.example.disneycodechallenge_generic.di

import com.example.disneycodechallenge_generic.rest.UserRepository
import com.example.disneycodechallenge_generic.rest.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository
}