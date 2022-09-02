package com.example.fragmentsappcat23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fragmentsappcat23.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), Communicator {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun sendData(message: String) {
        // here you implement the logic needed
        // to move data from one fragment to another
        Log.d(TAG, "sendData: Sending data to another fragment $message")

        (supportFragmentManager
            .findFragmentById(R.id.fragment_three) as ThirdFragment)
            .also {
                it.receivingData(message)
            }
    }

    override fun sendDataToSecondFragment(message: String) {
        (supportFragmentManager
            .findFragmentById(R.id.fragment_two) as SecondFragment)
            .also {
                it.receiveData(message)
            }
    }
}