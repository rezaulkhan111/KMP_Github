package com.machinecode.kmp_github.data.repository

import com.machinecode.kmp_github.data.model.RepositoryResponseDTO
import com.machinecode.kmp_github.data.network.GithubApiService
import com.machinecode.kmp_github.utils.ApiResult

class GithubRepository(private val apiService: GithubApiService) {
    suspend fun fetchRepository(mQuery: String, mPage: Int, mPerPage: Int): ApiResult<RepositoryResponseDTO> {
        return try {
            val response = apiService.getRepository(mutableMapOf<String, Any>().apply {
                put("q", mQuery)
                put("page", mPage)
                put("per_page", mPerPage)
            })

            val responseData = response.apply {
                mapData()
            }

            ApiResult.Success(responseData)
        } catch (exp: Exception) {
            exp.printStackTrace()
            ApiResult.Error(exp)
        }
    }
}