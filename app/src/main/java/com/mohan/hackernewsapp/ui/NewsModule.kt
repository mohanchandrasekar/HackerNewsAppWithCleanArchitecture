package com.mohan.hackernewsapp.ui

import com.mohan.domain.Comments
import com.mohan.domain.Story
import com.mohan.domain.UseCase
import com.mohan.domain.news.GetCommentItemUseCase
import com.mohan.domain.news.GetStoryItemUseCase
import com.mohan.domain.news.GetStoryUseCase
import com.mohan.hackernewsapp.ui.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsModule = module {

    viewModel {
        NewsViewModel(
            get(named("getStoryUseCase")),
            get(named("getStoryItemUseCase")),
            get(named("getCommentUseCase"))
        )
    }

    single<UseCase<Nothing?, List<Long>>>(named("getStoryUseCase")) {
        GetStoryUseCase(get())
    }

    single<UseCase<Long, Story>>(named("getStoryItemUseCase")) {
        GetStoryItemUseCase(get())
    }

    single<UseCase<Long, Comments>>(named("getCommentUseCase")) {
        GetCommentItemUseCase(get())
    }
}