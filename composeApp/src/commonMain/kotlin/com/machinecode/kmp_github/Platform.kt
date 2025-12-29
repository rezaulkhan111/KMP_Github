package com.machinecode.kmp_github

enum class Platform {
    ANDROID, IOS
}

expect fun getPlatform(): Platform