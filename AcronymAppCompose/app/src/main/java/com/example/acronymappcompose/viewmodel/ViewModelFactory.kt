package com.example.acronymappcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val acronymDataUseCase: AcronymDataUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AcronymViewModel(acronymDataUseCase, ioDispatcher) as T
}