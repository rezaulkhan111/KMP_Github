package com.machinecode.kmp_github.data.network

import com.machinecode.kmp_github.data.model.RepositoryResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubApiService {

    suspend fun getRepository(queryData: Map<String, Any>): RepositoryResponseDTO {
        return httpClient.get("search/repositories") {
            url {
                queryData.forEach { (key, value) ->
                    parameters.append(key, value.toString())
                }
            }
        }.body()
    }
}