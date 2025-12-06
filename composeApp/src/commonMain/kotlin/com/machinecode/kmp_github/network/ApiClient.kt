package com.machinecode.kmp_github.network

import com.machinecode.kmp_github.provideEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


// Shared HttpClient
//val httpClient = HttpClient(provideEngine()) {
//    install(ContentNegotiation) {
//        json(
//            Json {
//                prettyPrint = true
//                ignoreUnknownKeys = true
//                isLenient = true
//            })
//    }
//}