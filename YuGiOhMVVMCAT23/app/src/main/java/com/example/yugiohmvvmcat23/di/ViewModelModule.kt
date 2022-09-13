package com.example.yugiohmvvmcat23.di

import androidx.lifecycle.ViewModelProvider
import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.utils.CardsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideViewModelFactory(
        repository: YuGiOhRepository
    ): CardsViewModelFactory =
        CardsViewModelFactory(repository)
}