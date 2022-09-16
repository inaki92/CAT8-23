package com.example.schoolsapp.rest

import com.example.schoolsapp.model.SatScoresItem
import com.example.schoolsapp.model.SchoolItem
import retrofit2.Response
import retrofit2.http.GET

interface SchoolsApi {

    @GET(SCHOOLS_PATH)
    suspend fun getAllSchools(): Response<List<SchoolItem>>

    @GET(SCORES_PATH)
    suspend fun getSatScores(): Response<List<SatScoresItem>>

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val SCHOOLS_PATH = "s3k6-pzi2.json"
        private const val SCORES_PATH = "f9bf-2cp4.json"
    }
}