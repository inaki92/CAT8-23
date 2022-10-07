package com.example.acronymappcompose.usecase

import androidx.work.WorkManager
import com.example.acronymappcompose.rest.AcronymWorkRequest
import com.example.acronymappcompose.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AcronymDataUseCase @Inject constructor(
    private val workManager: WorkManager
) {

    private val _dataResult: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING)
    val dataResult: StateFlow<UIState> get() = _dataResult

    fun updateState(uiState: UIState) = let { _dataResult.value = uiState }

    fun enqueueWork(acronym: String) {
        workManager.enqueue(
            AcronymWorkRequest.createOneTimeRequest(acronym)
        )
    }
}