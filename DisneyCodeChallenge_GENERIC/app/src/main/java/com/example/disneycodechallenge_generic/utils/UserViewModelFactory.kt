package com.example.disneycodechallenge_generic.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.disneycodechallenge_generic.rest.UserRepository
import com.example.disneycodechallenge_generic.viewmodel.UsersViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(
    private val repository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(repository, ioDispatcher) as T
    }
}