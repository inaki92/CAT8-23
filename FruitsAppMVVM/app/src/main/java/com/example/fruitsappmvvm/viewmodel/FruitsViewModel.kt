package com.example.fruitsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitsappmvvm.model.Fruit
import com.example.fruitsappmvvm.rest.FruitsRepository
import com.example.fruitsappmvvm.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsViewModel @Inject constructor(
    private val repository: FruitsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _fruits: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val fruits: LiveData<UIState> get() = _fruits

    private val _fruitCreated: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val fruitCreated: LiveData<UIState> get() = _fruitCreated

    init {
        getFruits()
    }

    private fun getFruits() {
        viewModelScope.launch(ioDispatcher) {
            repository.getFruits().collect {
                _fruits.postValue(it)
            }
        }
    }

    fun createFruit(fruit: Fruit) {
        viewModelScope.launch(ioDispatcher) {
            repository.createFruit(fruit).collect {
                _fruitCreated.postValue(it)
            }
        }
    }
}