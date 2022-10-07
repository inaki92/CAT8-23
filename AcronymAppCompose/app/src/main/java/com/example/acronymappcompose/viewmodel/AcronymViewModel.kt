package com.example.acronymappcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.usecase.AcronymDataUseCase
import com.example.acronymappcompose.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AcronymViewModel(
    private val useCase: AcronymDataUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _meanings: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val meanings: LiveData<UIState> get() = _meanings

    fun getMeaningOf(acronym: String) {
        viewModelScope.launch(ioDispatcher) {
            useCase.dataResult.collect {
                _meanings.postValue(it)
            }
        }

        useCase.enqueueWork(acronym)
    }
}