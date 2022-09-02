package com.example.appinternetconectioncat23

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityState(
    private val connectivityManager: ConnectivityManager
) {

    fun checkInternetConnection(): Boolean {
        return connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

}