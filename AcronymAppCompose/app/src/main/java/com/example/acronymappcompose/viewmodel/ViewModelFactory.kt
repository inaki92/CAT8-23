package com.example.acronymappcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acronymappcompose.rest.AcronymRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: AcronymRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AcronymViewModel(repository) as T
}