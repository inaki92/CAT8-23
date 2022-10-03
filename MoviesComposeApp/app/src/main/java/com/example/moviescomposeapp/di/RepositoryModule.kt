package com.example.moviescomposeapp.di

import com.example.moviescomposeapp.rest.MoviesRepository
import com.example.moviescomposeapp.rest.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        repositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository
}