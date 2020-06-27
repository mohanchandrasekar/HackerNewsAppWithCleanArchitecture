package com.mohan.data

import com.mohan.data.repoository.HackerNewsService
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        createRetrofit(
            BASE_URL,
            Moshi.Builder().build()
        )
    }
    single { createHackerNewsService(get()) }
    factory { ErrorHandler() }
}

fun createRetrofit(baseUrl: String, moshi: Moshi): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}

fun createHackerNewsService(retrofit: Retrofit): HackerNewsService {
    return retrofit.create(HackerNewsService::class.java)
}

private const val BASE_URL = "https://hacker-news.firebaseio.com"