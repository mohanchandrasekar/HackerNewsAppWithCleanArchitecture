package com.mohan.domain.news

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetStoryUseCaseTest {
    private lateinit var subject: GetStoryUseCase
    private val newsRepository: NewsRepository = mock(NewsRepository::class.java)


    @Before
    fun setUp() {
        subject = GetStoryUseCase(newsRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeUseCase() = runBlockingTest {
        //Act
        subject.execute(null)

        //Assert
        verify(newsRepository).getStories()
//        verifyNoInteractions(newsRepository)
    }
}