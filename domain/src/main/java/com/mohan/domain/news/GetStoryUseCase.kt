package com.mohan.domain.news

import com.mohan.domain.State
import com.mohan.domain.UseCase

class GetStoryUseCase(private val newsRepository: NewsRepository) : UseCase<Nothing?, List<Long>>() {
    override suspend fun execute(params: Nothing?): State<List<Long>> {
        return newsRepository.getStories()
    }
}