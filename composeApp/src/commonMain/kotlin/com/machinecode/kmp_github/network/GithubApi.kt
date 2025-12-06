package com.machinecode.kmp_github.network

import com.machinecode.kmp_github.model.GithubUser
import com.machinecode.kmp_github.model.RepositoryResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubApi {

    suspend fun getRepository(username: String): RepositoryResponseDTO {
//        return httpClient.get("https://api.github.com/search/repositories$username").body()
        return RepositoryResponseDTO()
    }

    suspend fun getRepository(queryData: Map<String, Any>): RepositoryResponseDTO {
//        return httpClient.get("https://api.github.com/search/repositories") {
//            url {
//                queryData.forEach { (key, value) ->
//                    parameters.append(key, value.toString())
//                }
//            }
//        }.body()
        return RepositoryResponseDTO()
    }
}