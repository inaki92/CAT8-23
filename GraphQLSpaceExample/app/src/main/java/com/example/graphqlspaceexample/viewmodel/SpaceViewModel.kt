package com.example.graphqlspaceexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlspaceexample.network.SpaceRepository
import com.example.graphqlspaceexample.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpaceViewModel(
    private val repository: SpaceRepository
) : ViewModel() {

    init {
        getLaunch()
    }

    private val _launches: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val launches: LiveData<UIState> get() = _launches

    private fun getLaunch() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLaunchedRockets().collect {
                _launches.postValue(it)
            }
        }
    }
}