package com.mohan.hackernewsapp.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohan.domain.Comments
import com.mohan.domain.State
import com.mohan.domain.Story
import com.mohan.domain.UseCase
import com.mohan.hackernewsapp.ui.utils.startUseCaseWithResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getStoryUseCase: UseCase<Nothing?, List<Long>>,
    private val getStoryItemUseCase: UseCase<Long, Story>,
    private val getCommentItemUseCase: UseCase<Long, Comments>
) : ViewModel() {

    private val storyIdListLiveData: MutableLiveData<State<List<Long>>> = MutableLiveData()
    private val _storyData: MutableLiveData<MutableList<Story>> = MutableLiveData()

    private var storyList = mutableListOf<Story>()
    private val commentsListLiveData: MutableLiveData<State<List<Comments>>> = MutableLiveData()

    val storyData: LiveData<MutableList<Story>>
        get() = _storyData

    fun getTopStory() = startUseCaseWithResult(getStoryUseCase, null)

    fun updateStoryId(topStory: List<Long>) {
        for (id in topStory) {
            CoroutineScope(Dispatchers.IO).launch {
                val async = async {
                    when (val it = startUseCaseWithResult(getStoryItemUseCase, id)) {
                        is State.Success -> {
                            storyList.add(it.data)
                            Log.e(TAG, "Data : ${it.data}")
                        }
                        is State.Failure -> "Something happed"
                        else -> {
                            "Something happed"
                        }
                    }
                }
                async.await()
                _storyData.postValue(storyList)
            }
        }

    }
}

private const val TAG = "NewsViewModel"
