package com.example.fruitsappmvpcat23.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.fruitsappmvpcat23.database.FruitsDAO
import com.example.fruitsappmvpcat23.database.FruitsDatabase
import com.example.fruitsappmvpcat23.database.MIGRATION_1_2
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context =
        application.applicationContext

    @Provides
    fun providesDatabaseBuilder(context: Context): FruitsDatabase =
        Room.databaseBuilder(
            context,
            FruitsDatabase::class.java,
            "fruits-db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun providesFruitsDao(database: FruitsDatabase): FruitsDAO =
        database.getFruitsDao()

}