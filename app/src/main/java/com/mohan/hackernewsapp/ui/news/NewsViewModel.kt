package com.mohan.hackernewsapp.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.Story
import com.mohan.domain.UseCase
import com.mohan.hackernewsapp.ui.utils.startUseCaseWithResult

class NewsViewModel(
    private val getStoryUseCase: UseCase<Nothing?, List<Long>>,
    private val getStoryItemUseCase: UseCase<Long, Story>,
    private val getCommentItemUseCase: UseCase<Long, Comments>
) : ViewModel() {

    private val storyIdListLiveData: MutableLiveData<State<List<Long>>> = MutableLiveData()
    private val storyListLiveData: MutableLiveData<State<List<Story>>> = MutableLiveData()
    private val commentsListLiveData: MutableLiveData<State<List<Comments>>> = MutableLiveData()

    fun getTopStory() = startUseCaseWithResult(getStoryUseCase, null)

    fun updateStoryId(topStory: List<Long>) {
        for (id in topStory) {
            val startUseCaseWithResult = startUseCaseWithResult(getStoryItemUseCase, id)
            Log.e("Mohan", "startUseCaseWithResult : ${startUseCaseWithResult.toString()}")
        }
    }
}