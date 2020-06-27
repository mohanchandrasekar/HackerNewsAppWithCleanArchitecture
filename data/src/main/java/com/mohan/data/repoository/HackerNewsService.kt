package com.mohan.data.repoository

import com.mohan.domain.Comments
import com.mohan.domain.Story
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    /**
     * Return a list of the latest post IDs.
     */
    @GET("/v0/topstories.json")
    suspend fun getTopStories(): List<Long>

    /**
     * Return story item.
     */
    @GET("/v0/item/{itemId}.json")
    suspend fun getStoryItem(@Path("itemId") itemId: Long): Story

    /**
     * Returns a comment item.
     */
    @GET("/v0/item/{itemId}.json")
    suspend fun getCommentItem(@Path("itemId") itemId: Long): Comments
}