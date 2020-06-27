package com.mohan.hackernewsapp.ui.utils

import android.app.Application
import com.mohan.data.networkModule
import com.mohan.data.repositoryModule
import com.mohan.hackernewsapp.ui.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HackerNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HackerNewsApplication)
            modules(networkModule,
                newsModule, repositoryModule)
        }
    }
}