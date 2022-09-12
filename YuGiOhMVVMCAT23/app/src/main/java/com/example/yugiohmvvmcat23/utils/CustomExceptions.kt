package com.example.yugiohmvvmcat23.utils

class NullResponseFromServer(message: String) : Exception(message)
class FailureResponseFromServer(message: String?) : Exception(message)