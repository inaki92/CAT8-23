package com.example.schoolsapp.model.domain

import com.example.schoolsapp.model.SchoolItem

data class School(
    val schoolDbn: String,
    val schoolName: String,
    val schoolAddress: String,
    val schoolWebsite: String,
    val schoolPhone: String,
    val schoolEmail: String
)

fun List<SchoolItem>.mapToDomainSchools(): List<School> =
    this.map {
        School(
            schoolDbn = it.dbn ?: "",
            schoolAddress = it.location ?: "",
            schoolEmail = it.schoolEmail ?: "",
            schoolPhone = it.phoneNumber ?: "",
            schoolName = it.schoolName ?: "",
            schoolWebsite = it.website ?: ""
        )
    }
