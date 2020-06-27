package com.mohan.hackernewsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mohan.domain.State
import com.mohan.hackernewsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (val topStory = newsViewModel.getTopStory()) {
            is State.Success -> {
                newsViewModel.updateStoryId(topStory.data); }
            is State.Failure -> {
                Log.e(TAG, topStory.message)
            }
        }
    }
}