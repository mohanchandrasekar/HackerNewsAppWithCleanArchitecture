package com.mohan.domain.news

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetStoryItemUseCaseTest {
    private lateinit var subject: GetStoryItemUseCase
    private val newsRepository: NewsRepository = mock(NewsRepository::class.java)


    @Before
    fun setUp() {
        subject = GetStoryItemUseCase(newsRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeUseCase() = runBlockingTest {

        //Arrange
        val storyItem: Long = 111

        //Act
        subject.execute(storyItem)

        //Assert
        verify(newsRepository).getStoryItem(storyItem)
//        verifyNoInteractions(newsRepository)

    }
}