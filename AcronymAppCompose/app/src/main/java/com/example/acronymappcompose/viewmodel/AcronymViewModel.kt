package com.example.acronymappcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymappcompose.rest.AcronymRepository
import com.example.acronymappcompose.utils.UIState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AcronymViewModel(
    private val repository: AcronymRepository
) : ViewModel() {

    private val _meanings: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val meanings: LiveData<UIState> get() = _meanings

    fun getMeaningOf(acronym: String) {
        viewModelScope.launch {
            repository.getMeaning(acronym)
                .catch {
                    _meanings.postValue(UIState.ERROR(it))
                }
                .collect {
                    _meanings.postValue(it)
                }
        }
    }
}