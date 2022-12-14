package com.example.fruitsappmvpcat23.di

import com.example.fruitsappmvpcat23.MainActivity
import com.example.fruitsappmvpcat23.view.AllFragment
import com.example.fruitsappmvpcat23.view.CreateFruitFragment
import com.example.fruitsappmvpcat23.view.SearchFruitFragment
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        PresentersModule::class,
        RepositoriesModule::class
    ]
)
interface FruitsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(allFruitsFragment: AllFragment)
    fun inject(searchFruitFragment: SearchFruitFragment)
    fun inject(createFruitFragment: CreateFruitFragment)
}