package com.example.schoolsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolsapp.model.domain.School
import com.example.schoolsapp.model.domain.Score
import com.example.schoolsapp.rest.SchoolsRepository
import com.example.schoolsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel @Inject constructor(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var school: School? = null

    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _score: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val scores: LiveData<UIState> get() {
        return when (val state = _score.value) {
            is UIState.LOADING -> { _score }
            is UIState.SUCCESS<*> -> {
                val score = (state.data as List<Score>)
                    .firstOrNull { it.dbn.contains(school?.schoolDbn ?: "") }
                MutableLiveData(UIState.SUCCESS(score))
            }
            is UIState.ERROR -> { _score }
            else -> { _score }
        }
    }

    init {
        getScores()
        getSchools()
    }

    private fun getSchools() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getSchools().collect {
                _schools.postValue(it)
            }
        }
    }

    private fun getScores() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getScoresBySchoolDbn()
                .catch { _score.postValue(UIState.ERROR(Exception(it))) }
                .collect {
                    _score.postValue(it)
                }
        }
    }
}