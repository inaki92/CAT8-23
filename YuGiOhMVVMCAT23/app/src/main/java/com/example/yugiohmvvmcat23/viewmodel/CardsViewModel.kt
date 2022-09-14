package com.example.yugiohmvvmcat23.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.rest.YuGiOhRepositoryImpl
import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

private const val TAG = "CardsViewModel"

class CardsViewModel(
    private val repository: YuGiOhRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    // this is a backfield variable
    // Mutable livedata can change the value but it will be changing in the viewModel only
    private val _spellCards: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)

    // LiveData object is a immutable variable that weill get the value from the mutable and them emit that top the View
    // Main purpose of it, your VIEW should be unable to change the LIVE DATA state or value
    // this is read-only variable
    val spellCards: LiveData<UIState> get() = _spellCards


//    init {
//        // here you can perform some operation once the viewmodel gets created
//        Log.d(TAG, "ViewModel INIT: ViewModel created")
//    }

    fun getCardsByType(cardType: CardType) {
        viewModelScope.launch(ioDispatcher) {
            // HERE WEW ARE IN THE WORKER THREAD
            repository.getCardByType(cardType).collect {
                withContext(Dispatchers.Main) {
                    // HERE WE CHANGED TO THE MAIN THREAD
                    // set value from the LIVE DATA needs to be called in the MAIN THREAD
                    // _spellCards.value = it
                }
                // post value from the LIVE DATA can be called in the main thread as well as the worker thread
                // this will keep only the last value emitted, and will be slower than the set value
                _spellCards.postValue(it)
            }
        }
    }

    fun getCardByName(cardName: String) {
        viewModelScope.launch {
            repository.getCardByName(cardName).collect {
                _spellCards.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel got cleared")
    }
}