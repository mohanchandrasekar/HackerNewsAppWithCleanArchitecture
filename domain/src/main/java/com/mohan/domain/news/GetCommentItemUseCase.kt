package com.mohan.domain.news

import com.mohan.domain.State
import com.mohan.domain.UseCase
import com.mohan.domain.Comments

class GetCommentItemUseCase(private val newsRepository: NewsRepository) :
    UseCase<Long, Comments>() {
    override suspend fun execute(params: Long): State<Comments> {
        return newsRepository.getCommentItem(params)
    }
}