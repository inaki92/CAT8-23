package com.example.yugiohmvvmcat23.di

import com.example.yugiohmvvmcat23.MainActivity
import com.example.yugiohmvvmcat23.view.SpellCardsFragment
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface YuGiOhComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(spellCardsFragment: SpellCardsFragment)
}