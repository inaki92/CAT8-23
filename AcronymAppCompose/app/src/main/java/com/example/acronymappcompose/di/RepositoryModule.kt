package com.example.acronymappcompose.di

import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.rest.AcronymRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(
        repositoryImpl: AcronymRepositoryImpl
    ): AcronymRepository
}