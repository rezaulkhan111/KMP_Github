package com.machinecode.kmp_github.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration(this)
        modules(commonModule)
    }
}

expect fun initKoinPlatform(): KoinApplication
//object KoinInitializer {
//    fun init(appDeclaration: (KoinApplication.() -> Unit)? = null) {
//        println("KOIN START CALLED")
//
//        startKoin {
//            appDeclaration?.invoke(this)
//            modules(commonModule)
//        }
//    }
//}