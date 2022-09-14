package com.example.yugiohmvvmcat23.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CardsViewModelTest {

    @get:Rule val taskRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var testObject:  CardsViewModel

    private val mockYuGiOhRepository = mockk<YuGiOhRepository>(relaxed = true)

    // this is another way to create a mock object with annotations
//    @MockK(relaxed = true)
//    lateinit var mockRepository: YuGiOhRepository

    @Before
    fun setUp() {
        // here you are initialising the annotation and all the objects needed
        // MockKAnnotations.init()
        testObject = CardsViewModel(mockYuGiOhRepository, testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get cards by type when fetching cards from the server returns a success response`() {
        // AAA
        // ASSIGN - here you will prepare the objects for testing
        val cardType = CardType.SPELL_CARD
        val states = mutableListOf<UIState>()
        every { mockYuGiOhRepository.getCardByType(cardType) } returns flowOf(
            UIState.SUCCESS(listOf(mockk(), mockk(), mockk()))
        )

        testObject.spellCards.observeForever {
            states.add(it)
        }

        // ACTION - here you perform the action to be tested
        testObject.getCardsByType(cardType)

        // ASSERTION - here you assert values fro the testing
        assertEquals(2, states.size)
        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
        assertEquals(3, (states[1] as UIState.SUCCESS).cards.size)
    }

    @Test
    fun `get cards by type when fetching cards from the server returns a error response`() {
        // AAA
        // ASSIGN - here you will prepare the objects for testing
        val cardType = CardType.SPELL_CARD
        val states = mutableListOf<UIState>()
        every { mockYuGiOhRepository.getCardByType(cardType) } returns flowOf(
            UIState.ERROR(Exception("Error"))
        )

        testObject.spellCards.observeForever {
            states.add(it)
        }

        // ACTION - here you perform the action to be tested
        testObject.getCardsByType(cardType)

        // ASSERTION - here you assert values fro the testing
        assertEquals(2, states.size)
        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(UIState.ERROR::class.java)
        assertThat((states[1] as UIState.ERROR).error.localizedMessage).isEqualTo("Error")
    }
}