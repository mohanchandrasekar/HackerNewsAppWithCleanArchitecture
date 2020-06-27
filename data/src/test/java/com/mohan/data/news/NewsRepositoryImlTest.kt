package com.mohan.data.news

import com.mohan.data.ErrorHandler
import com.mohan.data.repoository.HackerNewsService
import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.State.Failure
import com.mohan.domain.Story
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.HttpException

class NewsRepositoryImlTest {

    private lateinit var subject: NewsRepositoryImpl
    private val hackerNewsService = mock(HackerNewsService::class.java)
    private val errorHandler = mock(ErrorHandler::class.java)

    @Before
    fun setUp() {
        subject = NewsRepositoryImpl(hackerNewsService, errorHandler)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun successInGetStories() = runBlockingTest {

        val expected: ArrayList<Long>? = null
        val actualState: State<List<Long>> = subject.getStories()

        `when`(hackerNewsService.getTopStories()).thenReturn(expected)
        verify(hackerNewsService).getTopStories()
//        verifyNoInteractions(hackerNewsService)

        assertEquals(State.Success(expected), actualState)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun successInGetStoryItem() = runBlockingTest {
        val storyId: Long = 1112

        val mockParams =
            mock(Story::class.java)

        `when`(hackerNewsService.getStoryItem(storyId)).thenReturn(mockParams)

        val actualState = subject.getStoryItem(storyId)

        verify(hackerNewsService).getStoryItem(storyId)
//        verifyNoInteractions(hackerNewsService)

        assertEquals(State.Success(mockParams), actualState)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun successInGetCommentItem() = runBlockingTest {

        val commentId: Long = 1111

        val mockParams = mock(Comments::class.java)

        `when`(hackerNewsService.getCommentItem(commentId)).thenReturn(mockParams)

        val actualState = subject.getCommentItem(commentId)

        verify(hackerNewsService).getCommentItem(commentId)

        assertEquals(State.Success(mockParams), actualState)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun failureInGetAllStory() = runBlockingTest {

        val exception = mock(HttpException::class.java)
        val message = "Error"

        `when`(hackerNewsService.getTopStories()).thenThrow(exception)
        `when`(errorHandler.handleException(exception)).thenReturn(message)

        val actualState = subject.getStories()

        verify(hackerNewsService).getTopStories()
//        verifyNoInteractions(hackerNewsService)

        assertEquals(
            Failure<List<Long>>(errorHandler.handleException(exception)),
            actualState
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun failureInGetStoryItem() = runBlockingTest {

        val id: Long = 111
        val exception = mock(HttpException::class.java)

        val message = "HttpException"

        `when`(hackerNewsService.getStoryItem(id)).thenThrow(exception)
        `when`(errorHandler.handleException(exception)).thenReturn(message)

        val actualState = subject.getStoryItem(id)

        verify(hackerNewsService).getStoryItem(id)

        assertEquals(Failure<Story>(errorHandler.handleException(exception)), actualState)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun failureInGetCommentItem() = runBlockingTest {
        val id: Long = 111
        val exception = mock(HttpException::class.java)

        val message = "HttpException"

        `when`(hackerNewsService.getCommentItem(id)).thenThrow(exception)
        `when`(errorHandler.handleException(exception)).thenReturn(message)

        val actualState = subject.getCommentItem(id)

        verify(hackerNewsService).getCommentItem(id)

        assertEquals(Failure<Story>(errorHandler.handleException(exception)), actualState)
    }
}