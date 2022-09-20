package com.example.schoolsapp.utils

import com.example.schoolsapp.model.domain.School

sealed class ItemView {
    data class HeaderItem(val character: String): ItemView()
    data class SchoolItem(val school: School): ItemView()
}
