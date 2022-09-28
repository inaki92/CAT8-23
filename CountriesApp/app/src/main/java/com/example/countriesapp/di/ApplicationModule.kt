package com.example.countriesapp.di

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun providesFusedLocationProvider(
        context: Context
    ):  FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun providesNotificationManager(
        context: Context
    ): NotificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
}