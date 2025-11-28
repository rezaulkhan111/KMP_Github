package com.machinecode.kmp_github

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform