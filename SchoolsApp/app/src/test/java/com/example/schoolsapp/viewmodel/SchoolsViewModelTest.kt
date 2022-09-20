package com.example.schoolsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.schoolsapp.model.domain.School
import com.example.schoolsapp.rest.SchoolsRepository
import com.example.schoolsapp.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsViewModelTest {

    @get:Rule val rule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockRepository = mockk<SchoolsRepository>(relaxed = true)

    private lateinit var testObject: SchoolsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get schools when response retrieves a list of schools returns success`() {
        // ASSIGN - GIVEN
        every { mockRepository.getSchools() } returns flowOf(
            UIState.SUCCESS(listOf<School>(mockk(), mockk(), mockk()))
        )
        val states = mutableListOf<UIState>()

        // ACTION
        testObject = SchoolsViewModel(mockRepository, testDispatcher)

        testObject.schools.observeForever {
            states.add(it)
        }

        // ASSERTION
        assertThat(states).hasSize(1)
        assertThat(states[0]).isInstanceOf(UIState.SUCCESS::class.java)
        assertThat((states[0] as UIState.SUCCESS<List<School>>).data).hasSize(3)
    }
}