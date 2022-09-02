package com.example.firstactivityapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyData(val name: String): Parcelable
