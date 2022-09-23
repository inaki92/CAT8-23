package com.example.spotifyapp.di

import com.example.spotifyapp.rest.SpotifyRepository
import com.example.spotifyapp.rest.SpotifyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun providesRepository(
        repository: SpotifyRepositoryImpl
    ): SpotifyRepository
}