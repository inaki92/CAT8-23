package com.example.acronymappcompose.di

import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.viewmodel.AcronymViewModel
import com.example.acronymappcompose.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        repository: AcronymRepository
    ): ViewModelFactory =
        ViewModelFactory(repository)
}