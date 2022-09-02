package com.example.firstactivityapp

import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.firstactivityapp.databinding.ActivityMain2Binding
import com.example.firstactivityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("MY_SHARE_PREFS", MODE_PRIVATE)
    }

    // baseContext -> it is the activity CONTEXT that will alive when activity is active
    // applicationContext -> this application context will be alive when app is running

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // ACTIVITY IS NOT VISIBLE TO THE USER
        // here you are inflating the UI layout and creating the instance of the activity to be used
        // this will be called only once
        Log.d(TAG, "onCreate: Activity created")
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

        // here you are retriving data from shared preferences
        val emailStored = sharedPreferences.getString("MY_EMAIL", null)
        binding.emailEt.setText(emailStored)

        binding.loginBtn.setOnClickListener {
            // right here you have the click action

            // here you are storing data to shared preferences
            sharedPreferences.edit().apply {
                putString("MY_EMAIL", binding.emailEt.text.toString())

                // apply will perform the operation without returning any value
                //apply()

                // commit will return the boolean value whenever is done
                // commit()
            }.apply()


//            AlertDialog.Builder(this)
//                .setTitle("Button has been clicked")
//                .setMessage("You have clicked the Login Button")
//                .setPositiveButton("OK") { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .setNegativeButton("DISMISS") { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .create()
//                .show()

            // Intent -> a message object that allows you to open other component within your or external apps

            // Explicit -> When you know the action you want to perform and the exact component to open
            // Implicit -> when you know the action but you do not know the component

            // Example EXPLICIT INTENT to open the main activity 2
            Intent(baseContext, MainActivity2::class.java).apply {
                // passing data to the next activity
                putExtra("MY_DATA", "This data is coming from activity one: ")
                putExtra("DATA_OBJECT", MyData("Android training"))
                startActivity(this)
            }

            // Example for IMPLICIT INTENT
            val mUri = Uri.parse("https://www.cnn.com/")
            Intent(ACTION_VIEW, mUri).apply {
                startActivity(this)
            }
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

    companion object {
        const val TAG = "MainActivity"
    }
}