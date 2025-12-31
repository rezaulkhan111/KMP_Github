package com.machinecode.kmp_github.data.network

import com.machinecode.kmp_github.data.model.RepositoryResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GithubApiService {

    suspend fun getRepository(queryData: Map<String, Any>): RepositoryResponseDTO {
        return httpClient.get("search/repositories") {
            queryData.forEach { (key, value) ->
                parameter(key, value.toString())
            }
        }.body()
    }
}