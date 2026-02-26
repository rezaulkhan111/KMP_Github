package com.machinecode.kmp_github

import android.app.Application
import com.machinecode.kmp_github.di.KoinInitializer
import org.koin.android.ext.koin.androidContext

class GithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init {
            androidContext(this@GithubApplication)
        }
//        initKoin {
//            androidContext(this@GithubApplication)
//        }
    }
}