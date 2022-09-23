package com.example.spotifyapp.model.authentication

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class TokenHandling @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    var authToken: String?
        get() = sharedPreferences.getString(AUTH_TOKEN, null)
        set(value) {
            sharedPreferences.edit().apply {
                putString(AUTH_TOKEN, value)
            }.apply()
        }

    val authorizationKey: String?
        get() {
            with("$CLIENT_ID:$CLIENT_SECRET".toByteArray()) {
                Base64.getEncoder().encodeToString(this).also {
                    sharedPreferences.edit().apply {
                        putString(AUTH_SECRET, it)
                    }.apply()
                }
            }

            return sharedPreferences.getString(AUTH_SECRET, null)
        }

    companion object {
        private const val AUTH_TOKEN = "AUTH_TOKEN"
        private const val AUTH_SECRET = "AUTH_SECRET"

        private const val CLIENT_ID = "f69c14aab33048adabe8febd3b2b75e8"
        private const val CLIENT_SECRET = "8489b3835c194d0785b7bcc9d25c4eb9"
    }

}