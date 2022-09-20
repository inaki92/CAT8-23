package com.example.schoolsapp.model.domain

import android.os.Parcelable
import com.example.schoolsapp.model.SchoolItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class School(
    val schoolDbn: String,
    val schoolName: String,
    val schoolAddress: String,
    val schoolLatitude: String,
    val schoolLongitude: String,
    val schoolWebsite: String,
    val schoolPhone: String,
    val schoolEmail: String,
    val schoolOverview: String
) : Parcelable

fun List<SchoolItem>.mapToDomainSchools(): List<School> =
    this.map {
        School(
            schoolDbn = it.dbn ?: "",
            schoolAddress = it.location ?: "",
            schoolEmail = it.schoolEmail ?: "",
            schoolPhone = it.phoneNumber ?: "",
            schoolName = it.schoolName ?: "",
            schoolWebsite = it.website ?: "",
            schoolLatitude = it.latitude ?: "",
            schoolLongitude = it.longitude ?: "",
            schoolOverview = it.overviewParagraph ?: ""
        )
    }
