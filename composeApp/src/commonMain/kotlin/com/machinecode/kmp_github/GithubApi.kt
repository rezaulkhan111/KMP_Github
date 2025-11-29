package com.machinecode.kmp_github

import com.machinecode.kmp_github.model.GithubUser
import com.machinecode.kmp_github.model.RepositoryResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubApi {
    suspend fun getUser(username: String): GithubUser {
        return httpClient.get("https://api.github.com/users/$username").body()
    }

    suspend fun getRepository(username: String): RepositoryResponseDTO {
        return httpClient.get("https://api.github.com/users/$username").body()
    }
}