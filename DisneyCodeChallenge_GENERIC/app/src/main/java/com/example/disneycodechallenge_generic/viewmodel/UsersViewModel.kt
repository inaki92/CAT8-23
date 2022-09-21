package com.example.disneycodechallenge_generic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycodechallenge_generic.rest.UserRepository
import com.example.disneycodechallenge_generic.utils.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getUsers()
    }

    private val _users: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val users: LiveData<ResultState> get() = _users

    private fun getUsers() {
        viewModelScope.launch(ioDispatcher) {
            repository.getUsers().collect {
                _users.postValue(it)
            }
        }
    }
}