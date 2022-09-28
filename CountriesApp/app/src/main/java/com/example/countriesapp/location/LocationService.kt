package com.example.countriesapp.location

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.countriesapp.di.CountriesApp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import javax.inject.Inject

private const val TAG = "LocationService"

class LocationService : Service() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
        CountriesApp.countriesComponent.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val interval = it.getIntExtra("INTERVAL", 3000)
            val notification = it.getParcelableExtra<Notification>("NOTIFICATION")

            startTrackingLocation(interval)

            startForeground(321, notification)
        }

        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    private fun startTrackingLocation(intervalProvided: Int) {
        try {
            fusedLocationProviderClient.requestLocationUpdates(
                LocationRequest.create().apply {
                    interval = intervalProvided.toLong()
                    fastestInterval = intervalProvided.toLong()
                    priority = PRIORITY_HIGH_ACCURACY
                },
                getPendingIntent(context)
            )
        } catch (e: Exception) {
            Log.e(TAG, "startTrackingLocation: error", e)
        }
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationProviderClient.removeLocationUpdates(getPendingIntent(context))
    }

    companion object {

        private fun locationIntent(
            context: Context,
            interval: Int? = null,
            notification: Notification? = null
        ) =
            Intent(context, LocationService::class.java).apply {
                putExtra("INTERVAL", interval)
                putExtra("NOTIFICATION", notification)
            }

        private fun getPendingIntent(context: Context) =
            PendingIntent.getBroadcast(context, 432, LocationReceiver.getIntent(context), PendingIntent.FLAG_UPDATE_CURRENT)

        fun startService(context: Context, interval: Int, notification: Notification) {
            context.startService(locationIntent(context, interval, notification))
        }

        fun stopService(context: Context) {
            context.stopService(locationIntent(context))
        }
    }
}