package com.example.composeapp

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import java.util.regex.Pattern

fun String.validateEmail(): Boolean =
    EMAIL_ADDRESS.matcher(this).matches()

fun String.validatePassword(): Boolean =
    Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
        .matcher(this).matches()