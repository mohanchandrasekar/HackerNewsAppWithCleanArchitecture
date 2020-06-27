package com.mohan.domain.news

import com.mohan.domain.State
import com.mohan.domain.UseCase
import com.mohan.domain.Story

class GetStoryItemUseCase(private val newsRepository: NewsRepository) : UseCase<Long, Story>() {
    override suspend fun execute(params: Long): State<Story> {
        return newsRepository.getStoryItem(params)
    }
}