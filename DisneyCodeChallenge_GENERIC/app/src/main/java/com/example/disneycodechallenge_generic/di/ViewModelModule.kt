package com.example.disneycodechallenge_generic.di

import com.example.disneycodechallenge_generic.rest.UserRepository
import com.example.disneycodechallenge_generic.utils.UserViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        repository: UserRepository,
        ioDispatcher: CoroutineDispatcher
    ): UserViewModelFactory =
        UserViewModelFactory(repository, ioDispatcher)

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}