package com.example.countriesapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.di.CountriesApp
import com.example.countriesapp.location.LocationService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notificationManager: NotificationManager

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CountriesApp.countriesComponent.inject(this)

        notificationManager.createNotificationChannel(
            NotificationChannel(
                "CHANNEL_ID",
                "NOTIFICATION_CHANNEL",
                NotificationManager.IMPORTANCE_HIGH
            )
        )

        binding.startLocation.setOnClickListener {
            LocationService.startService(
                applicationContext,
                10000,
                createNotification()
            )
        }

        binding.stopLocation.setOnClickListener {
            LocationService.stopService(applicationContext)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(applicationContext, "CHANNEL_ID")
            .setContentTitle("Tracking location")
            .setContentText("We are tracking your location")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }
}