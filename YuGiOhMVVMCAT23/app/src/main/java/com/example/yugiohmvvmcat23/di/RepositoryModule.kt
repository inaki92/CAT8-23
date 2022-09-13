package com.example.yugiohmvvmcat23.di

import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.rest.YuGiOhRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesYuGiOhRepository(
        impl: YuGiOhRepositoryImpl
    ): YuGiOhRepository
}