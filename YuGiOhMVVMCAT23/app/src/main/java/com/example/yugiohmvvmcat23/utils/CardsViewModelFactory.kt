package com.example.yugiohmvvmcat23.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.viewmodel.CardsViewModel

class CardsViewModelFactory(
    private val repository: YuGiOhRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardsViewModel(repository) as T
    }
}