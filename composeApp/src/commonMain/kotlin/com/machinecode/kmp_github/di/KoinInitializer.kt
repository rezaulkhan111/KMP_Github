package com.machinecode.kmp_github.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

//fun initKoin(appDeclaration: (KoinApplication.() -> Unit)? = null) {
//    startKoin {
//        appDeclaration?.invoke(this)
//        modules(commonModule)
//    }
//}

object KoinInitializer {
    fun init(appDeclaration: (KoinApplication.() -> Unit)? = null) {
        startKoin {
            appDeclaration?.invoke(this)
            modules(commonModule)
        }
    }
}