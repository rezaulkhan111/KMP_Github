package com.machinecode.kmp_github.network

import com.machinecode.kmp_github.model.GithubUser
import com.machinecode.kmp_github.model.RepositoryDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GithubVM {
    private val api = GithubApi()
    private val vmScope = CoroutineScope(Dispatchers.Default)

    private val _user = MutableStateFlow<GithubUser?>(null)
    val user: StateFlow<GithubUser?> = _user

    private val _repository = MutableStateFlow<List<RepositoryDTO>?>(null)
    val repository: StateFlow<List<RepositoryDTO>?> = _repository

    fun fetchRepository(mQuery: String) {
        vmScope.launch {
            try {
                val parameters = mapOf(
                    "q" to mQuery, "page" to 1, "per_page" to 10
                )
                val result = api.getRepository(parameters)
                _repository.value = result.items
            } catch (e: Exception) {
                println("Error loading GitHub user: $e")
                _repository.value = null
            }
        }
    }
}