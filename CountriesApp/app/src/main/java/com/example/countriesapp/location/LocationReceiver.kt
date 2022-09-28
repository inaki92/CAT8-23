package com.example.countriesapp.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.LocationResult

private const val TAG = "LocationReceiver"

class LocationReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent) {
        if (LocationResult.hasResult(intent)) {
            LocationResult.extractResult(intent)?.lastLocation?.let {
                Log.d(TAG, "onReceive: location: ${it.longitude}, ${it.latitude}")
            } ?: Log.e(TAG, "location is null")
        } else {
            Log.e(TAG, "onReceive: location has no result")
        }
    }

    companion object {
        fun getIntent(context: Context) =
            Intent(context, LocationReceiver::class.java)
    }
}