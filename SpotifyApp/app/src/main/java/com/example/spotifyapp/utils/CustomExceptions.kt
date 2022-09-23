package com.example.spotifyapp.utils

class NullBodyResponse(message: String) : Exception(message)
class FailureResponse(message: String?) : Exception(message)