package com.example.schoolsapp.rest

import com.example.schoolsapp.model.domain.School
import com.example.schoolsapp.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsRepositoryImplTest {

    private val mockSchoolsApi = mockk<SchoolsApi>(relaxed = true)

    private lateinit var schoolsRepository: SchoolsRepository

    @Before
    fun setUp() {
        schoolsRepository = SchoolsRepositoryImpl(mockSchoolsApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get schools when the network connection is success and body is not null returns a success state`()
    = runTest {
        // ASSIGN
        coEvery { mockSchoolsApi.getAllSchools() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(
                mockk(relaxed = true),
                mockk(relaxed = true),
                mockk(relaxed = true)
            )
        }
        val states = mutableListOf<UIState>()

        // ACTION
        schoolsRepository.getSchools().collect {
            states.add(it)
        }

        // ASSERTION
        assertThat(states).hasSize(2)
        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
        assertThat((states[1] as UIState.SUCCESS<List<School>>).data).hasSize(3)
    }

    @Test
    fun `get schools when the network connection is success and body is null throw Null response exception`()
            = runTest {
        // ASSIGN
        coEvery { mockSchoolsApi.getAllSchools() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }
        val states = mutableListOf<UIState>()

        // ACTION
        schoolsRepository.getSchools().collect {
            states.add(it)
        }

        // ASSERTION
        assertThat(states).hasSize(2)
        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(UIState.ERROR::class.java)
        assertThat((states[1] as UIState.ERROR).error.localizedMessage).isEqualTo("School response is null")
    }
}