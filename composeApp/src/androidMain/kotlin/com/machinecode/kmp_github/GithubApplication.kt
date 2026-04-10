package com.machinecode.kmp_github

import android.app.Application
import com.machinecode.kmp_github.di.initKoinAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class GithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinAndroid {
            androidLogger()
            androidContext(this@GithubApplication)
        }
    }
}