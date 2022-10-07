package com.example.acronymappcompose.utils

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.rest.AcronymWorkRequest
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import javax.inject.Inject

class WorkerFactoryImpl @Inject constructor(
    private val repository: AcronymRepository,
    private val useCase: AcronymDataUseCase
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return AcronymWorkRequest(
            appContext,
            workerParameters,
            repository,
            useCase
        )
    }

}