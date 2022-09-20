package com.example.schoolsapp.utils

import com.example.schoolsapp.model.domain.School

sealed class ClickHandler {
    data class WebsiteClick(val website: String) : ClickHandler()
    data class AddressClick(val longitude: String, val latitude: String) : ClickHandler()
    data class DetailsClick(val school: School) : ClickHandler()
}
