package com.mohan.domain.news

import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.UseCase

class GetCommentItemUseCase(private val newsRepository: NewsRepository) :
    UseCase<Long, Comments>() {
    override suspend fun execute(params: Long): State<Comments> {
        return newsRepository.getCommentItem(params)
    }
}