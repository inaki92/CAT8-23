package com.example.alarmapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast

private const val TAG = "AlarmReceiver"

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // here you can do your logic for the notification
        Log.d(TAG, "onReceive: alarm received")
        Toast.makeText(
            context,
            intent.getStringExtra("EVENT_TITLE"),
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val RECEIVER_ACTION = "com.example.alarmapp.alarmReceiver"
        private const val REQUEST_CODE = 89

        val intentFilter = IntentFilter(RECEIVER_ACTION)

        fun getIntent(context: Context, title: String, info: String): Intent =
            Intent(context, AlarmReceiver::class.java).apply {
                action = RECEIVER_ACTION
                putExtra("EVENT_TITLE", title)
                putExtra("INFO_EVENT", info)
            }

        fun getAlarmPendingIntent(context: Context, intent: Intent): PendingIntent =
            PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}