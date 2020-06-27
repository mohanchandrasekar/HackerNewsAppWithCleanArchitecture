package com.mohan.data

import com.mohan.data.news.NewsRepositoryImpl
import com.mohan.domain.news.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}