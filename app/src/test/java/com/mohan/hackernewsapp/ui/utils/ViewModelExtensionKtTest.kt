package com.mohan.hackernewsapp.ui.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohan.domain.State
import com.mohan.domain.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.*

class ViewModelExtensionKtTest {

    private var viewModel: ViewModel = ViewModelHelper()

    private val useCase: UseCase<Any?, Any?> =
        mock(UseCase::class.java) as UseCase<Any?, Any?>

    private val mutableLiveData: MutableLiveData<State<Any?>> =
        mock(MutableLiveData::class.java) as MutableLiveData<State<Any?>>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun startUseCaseTest() = runBlockingTest {

        //Arrange
        val param: Any = mock(Any::class.java)
        val result: State<Any?> = State.Success(null)

        //Act
        `when`(useCase.execute(param)).thenReturn(result)
        viewModel.startUseCase(useCase, mutableLiveData, param)

        val argumentCaptor =
            ArgumentCaptor.forClass(State::class.java) as ArgumentCaptor<State<Any?>>

        //Assert
        verify(mutableLiveData, times(2)).postValue(argumentCaptor.capture())
        verify(useCase).execute(param)
//        verifyNoInteractions(mutableLiveData, useCase)

        assertTrue(argumentCaptor.allValues[0] is State.Loading<Any?>)
        assertTrue(argumentCaptor.allValues[1] is State.Success<Any?>)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun startUseCaseWithResult() = runBlockingTest {

        //Arrange
        val params = mock(Any::class.java)
        val result: State<Any?> = State.Success(null)

        `when`(useCase.execute(params)).thenReturn(result)

        //Act
        val actual = viewModel.startUseCaseWithResult(useCase, params)

        //Assert
        verify(useCase).execute(params)
//        verifyNoInteractions(mutableLiveData, useCase)

        assertEquals(result, actual)
    }

    inner class ViewModelHelper : ViewModel()
}
