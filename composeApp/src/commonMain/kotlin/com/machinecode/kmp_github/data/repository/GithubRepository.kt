package com.machinecode.kmp_github.data.repository

import com.machinecode.kmp_github.data.network.GithubApiService

class GithubRepository(private val api: GithubApiService) {

    suspend fun getRepository(queryData: Map<String, Any>) = api.getRepository(queryData)
}