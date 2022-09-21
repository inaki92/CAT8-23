package com.example.disneycodechallenge_generic.rest

import android.content.Context
import com.example.disneycodechallenge_generic.model.User
import com.example.disneycodechallenge_generic.model.domain.mapToDomainUser
import com.example.disneycodechallenge_generic.utils.ResultState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UserRepository {
    fun getUsers(): Flow<ResultState>
}

class UserRepositoryImpl @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : UserRepository {

    override fun getUsers(): Flow<ResultState> = flow {
        emit(ResultState.LOADING)

        try {
            with(context.assets.open(FILE_NAME)
                .bufferedReader()
                .use { it.readText() }
            ) {
                val users = gson.fromJson<List<User>>(
                    this,
                    object : TypeToken<List<User>>() {}.type
                )

                emit(ResultState.SUCCESS(users.mapToDomainUser()))
            }
        } catch (e: Exception) {
            emit(ResultState.ERROR(e))
        }
    }

    companion object {
        private const val FILE_NAME = "disney_user.json"
    }
}