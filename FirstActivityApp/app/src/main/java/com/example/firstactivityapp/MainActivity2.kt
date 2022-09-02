package com.example.firstactivityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firstactivityapp.databinding.ActivityMain2Binding

private const val TAG = "MainActivity2"

class MainActivity2 : AppCompatActivity() {

    private val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // ACTIVITY WILL BE VISIBLE TO THE USER BUT CANNOT INTERACT
        // This is used to start services, broadcast receivers, listening to events
        Log.d(TAG, "onStart: Activity started")
    }

    override fun onResume() {
        super.onResume()
        // THIS IS WHERE THE USER IS ABLE TO START INTERACTING WITH THE ACTIVITY
        Log.d(TAG, "onResume: Activity resumed")

        // getting the data from the INTENT passed by previous activity
        intent.extras?.let {
            binding.secondTv.text = String.format(it.getString("MY_DATA") +
            it.getParcelable<MyData>("DATA_OBJECT")?.name)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Age", 12)
        Log.d(TAG, "onSaveInstanceState: Rotation of screen " + 12)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")
        Log.d(TAG, "onRestoreInstanceState: Restoring rotation $data")

    }

    override fun onPause() {
        super.onPause()
        // WHEN THE ACTIVITY IS VISIBLE TO THE USER BUT CANNOT INTERACT ANYMORE
        Log.d(TAG, "onPause: Activity paused")
    }

    override fun onStop() {
        super.onStop()
        // WHEN ACTIVITY IS NOT VISIBLE TO THE USER
        // here you will disconnect services, broadcast receivers, stop listening events
        Log.d(TAG, "onStop: Activity stopped")
    }

    override fun onRestart() {
        super.onRestart()
        // pulling the activity instance form the backstack
        Log.d(TAG, "onRestart: Activity restarted")
    }

    override fun onDestroy() {
        super.onDestroy()
        // THIS IS WHERE ACTIVITY GETS DESTROY FROM BACKSTACK AND INSTANCE IS DISPOSED
        // this method is not granted to be called unless you execute the finish() or killing the app
        Log.d(TAG, "onDestroy: Activity destroyed")
    }
}