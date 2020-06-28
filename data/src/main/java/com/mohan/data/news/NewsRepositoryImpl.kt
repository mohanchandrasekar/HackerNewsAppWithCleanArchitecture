package com.mohan.data.news

import com.mohan.data.ErrorHandler
import com.mohan.data.repoository.HackerNewsService
import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.Story
import com.mohan.domain.news.NewsRepository

class NewsRepositoryImpl(
    private val hackerNewsService: HackerNewsService,
    private val errorHandler: ErrorHandler
) : NewsRepository {
    override suspend fun getStories(): State<List<Long>> {
        return try {
            State.Success(hackerNewsService.getTopStories())
        } catch (e: Exception) {
            State.Failure(errorHandler.handleException(e))
        }
    }

    override suspend fun getStoryItem(item: Long): State<Story> {
        return try {
            State.Success(hackerNewsService.getStoryItem(item))
        } catch (e: Exception) {
            State.Failure(errorHandler.handleException(e))
        }
    }

    override suspend fun getCommentItem(item: Long): State<Comments> {
        return try {
            State.Success(hackerNewsService.getCommentItem(item))
        } catch (e: Exception) {
            State.Failure(errorHandler.handleException(e))
        }
    }
}