package com.example.disneycodechallenge_generic.di

import com.example.disneycodechallenge_generic.MainActivity
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface UserComponent {
    fun inject(mainActivity: MainActivity)
}