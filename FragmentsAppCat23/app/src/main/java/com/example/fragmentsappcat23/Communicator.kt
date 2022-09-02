package com.example.fragmentsappcat23

interface Communicator {
    fun sendData(message: String)
    fun sendDataToSecondFragment(message: String)
}