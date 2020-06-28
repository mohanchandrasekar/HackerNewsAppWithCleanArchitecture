package com.mohan.domain.news

import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.Story

interface NewsRepository {
    suspend fun getStories(): State<List<Long>>
    suspend fun getStoryItem(item: Long): State<Story>
    suspend fun getCommentItem(item: Long): State<Comments>
}