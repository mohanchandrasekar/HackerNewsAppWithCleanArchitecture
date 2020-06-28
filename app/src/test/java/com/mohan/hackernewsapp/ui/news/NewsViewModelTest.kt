package com.mohan.hackernewsapp.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.mohan.domain.State
import com.mohan.domain.Story
import com.mohan.domain.news.GetCommentItemUseCase
import com.mohan.domain.news.GetStoryItemUseCase
import com.mohan.domain.news.GetStoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class NewsViewModelTest {
    private lateinit var subject: NewsViewModel
    private val getStoryUseCase: GetStoryUseCase = mock(GetStoryUseCase::class.java)
    private val getStoryItemUseCase: GetStoryItemUseCase = mock(GetStoryItemUseCase::class.java)
    private val getCommentItemUseCase: GetCommentItemUseCase =
        mock(GetCommentItemUseCase::class.java)
    private val mutableLiveData: MutableLiveData<MutableList<Story>> =
        mock(MutableLiveData::class.java) as MutableLiveData<MutableList<Story>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        subject = NewsViewModel(getStoryUseCase, getStoryItemUseCase, getCommentItemUseCase)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTopStoryTest() = runBlockingTest {
        subject.getTopStory()

        verify(getStoryUseCase).execute(null)
//        verifyNoInteractions(getStoryUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateStoryIdTest() = runBlockingTest {

        //Arrange
        val list: List<Long> = listOf(1, 3, 3, 4)

        //Act
        subject.updateStoryId(list)


        val argumentCaptor: ArgumentCaptor<MutableList<Story>> =
            ArgumentCaptor.forClass(State::class.java) as ArgumentCaptor<MutableList<Story>>
//
//        //Assert
//        verify(mutableLiveData, times(1)).postValue(argumentCaptor.capture())

    }
}