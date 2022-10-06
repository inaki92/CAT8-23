package com.example.acronymappcompose.di

import com.example.acronymappcompose.MainActivity
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AcronymComponent {
    fun inject(mainActivity: MainActivity)
}