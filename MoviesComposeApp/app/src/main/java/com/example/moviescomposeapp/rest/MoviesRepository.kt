package com.example.moviescomposeapp.rest

import com.example.moviescomposeapp.model.mapToDomainItems
import com.example.moviescomposeapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MoviesRepository {
    fun getMovies(): Flow<UIState>
}

class MoviesRepositoryImpl @Inject constructor(
    private val serviceApi: MoviesApi
) : MoviesRepository {
    override fun getMovies(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceApi.getMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.mapToDomainItems()))
                } ?: throw Exception("Response bosy is null")
            } else {
                throw Exception("Failure response")
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}