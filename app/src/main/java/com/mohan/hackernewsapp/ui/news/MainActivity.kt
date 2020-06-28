package com.mohan.hackernewsapp.ui.news

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mohan.domain.State
import com.mohan.domain.Story
import com.mohan.hackernewsapp.R
import com.mohan.hackernewsapp.ui.adapter.StoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val storyAdapter by lazy {
        StoryAdapter { story: Story, i: Int ->
            onItemClick(story, i)
        }
    }

    private fun onItemClick(it: Story, position: Int) {
        //TODO:: implement comments view based on story selection
    }

    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()

        getStory()

        newsViewModel.storyData.observe(this, Observer {
            storyAdapter.setData(it)
        })

    }

    private fun getStory() {
        when (val topStory = newsViewModel.getTopStory()) {
            is State.Success -> {
                newsViewModel.updateStoryId(topStory.data); }
            is State.Failure -> {
                Log.e(TAG, topStory.message)
            }
        }
    }

    private fun setRecyclerView() {
        recycler_top_stories.let { recycler ->
            recycler.layoutManager = LinearLayoutManager(
                baseContext, LinearLayoutManager.VERTICAL, false
            )
            recycler.adapter = storyAdapter
        }
    }

    override fun onRefresh() {
        getStory()
    }
}