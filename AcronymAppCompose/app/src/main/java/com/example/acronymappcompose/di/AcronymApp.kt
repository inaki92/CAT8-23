package com.example.acronymappcompose.di

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkerFactory
import javax.inject.Inject

class AcronymApp  :  Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        acronymComponent = DaggerAcronymComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var acronymComponent: AcronymComponent
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(workerFactory).build()
    }
}