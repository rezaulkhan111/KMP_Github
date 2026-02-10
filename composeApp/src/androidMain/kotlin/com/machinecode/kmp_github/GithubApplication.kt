package com.machinecode.kmp_github

import android.app.Application
import com.machinecode.kmp_github.di.initKoin
import org.koin.android.ext.koin.androidContext

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GithubApplication)
        }
    }
}