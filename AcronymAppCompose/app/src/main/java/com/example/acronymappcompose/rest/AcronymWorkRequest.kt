package com.example.acronymappcompose.rest

import android.content.Context
import androidx.work.*
import com.example.acronymappcompose.model.domain.mapToDomainMeaning
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import com.example.acronymappcompose.utils.UIState
import java.time.Duration

class AcronymWorkRequest(
    context: Context,
    params: WorkerParameters,
    private val repository: AcronymRepository,
    private val acronymDataUseCase: AcronymDataUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        acronymDataUseCase.updateState(
            UIState.LOADING
        )

        val response = repository.getMeaning(
            inputData.getString("ACRONYM_NAME") ?: ""
        )

        return try {
            if(response.isSuccessful) {
                response.body()?.let {
                    acronymDataUseCase.updateState(
                        UIState.SUCCESS(it.mapToDomainMeaning())
                    )
                    Result.success()
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("Failure response")
            }
        } catch (e: Exception) {
            acronymDataUseCase.updateState(UIState.ERROR(e))
            Result.failure()
        }
    }

    companion object {

        fun createOneTimeRequest(acronym:  String) =
            OneTimeWorkRequest.Builder(AcronymWorkRequest::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .setInputData(
                    Data.Builder()
                        .putString("ACRONYM_NAME", acronym)
                        .build()
                )
                .addTag("REQUEST_ACRONYM_MEANING")
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    Duration.ofSeconds(20)
                )
                .build()
    }

}