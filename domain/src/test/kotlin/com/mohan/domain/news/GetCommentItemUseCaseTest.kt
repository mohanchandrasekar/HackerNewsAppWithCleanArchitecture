package com.mohan.domain.news

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class GetCommentItemUseCaseTest {
    private lateinit var subject: GetCommentItemUseCase
    private var newsRepository: NewsRepository = mock(NewsRepository::class.java)

    @Before
    fun setUp() {
        subject = GetCommentItemUseCase(newsRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeUseCase() = runBlockingTest {

        //Arrange
        val commentItem: Long = 1001

        //Act
        subject.execute(commentItem)

        //Assert
        verify(newsRepository).getCommentItem(commentItem)
        verifyNoMoreInteractions(newsRepository)

    }
}