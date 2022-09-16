package com.example.schoolsapp.di

import com.example.schoolsapp.rest.SchoolsRepository
import com.example.schoolsapp.rest.SchoolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        schoolsRepositoryImpl: SchoolsRepositoryImpl
    ): SchoolsRepository
}