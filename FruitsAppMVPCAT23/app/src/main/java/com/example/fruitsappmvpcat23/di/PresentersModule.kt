package com.example.fruitsappmvpcat23.di

import com.example.fruitsappmvpcat23.presenters.AllFruitsPresenter
import com.example.fruitsappmvpcat23.presenters.AllFruitsPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesAllFruitsPresenter(
        allFruitsPresenterImpl: AllFruitsPresenterImpl
    ): AllFruitsPresenter
}