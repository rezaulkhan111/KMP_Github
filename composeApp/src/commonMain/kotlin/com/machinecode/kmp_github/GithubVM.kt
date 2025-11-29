package com.machinecode.kmp_github

import com.machinecode.kmp_github.model.GithubUser
import com.machinecode.kmp_github.model.RepositoryResponseDTO
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

    private val _repository = MutableStateFlow<RepositoryResponseDTO?>(null)
    val repository: StateFlow<RepositoryResponseDTO?> = _repository

    fun fetchUser(username: String) {
        vmScope.launch {
            try {
                val result = api.getUser(username)
                _user.value = result
            } catch (e: Exception) {
                println("Error loading GitHub user: $e")
                _user.value = null
            }
        }
    }

    fun fetchRepository(username: String) {
        vmScope.launch {
            try {
                val result = api.getRepository(username)
                _repository.value = result
            } catch (e: Exception) {
                println("Error loading GitHub user: $e")
                _repository.value = null
            }
        }
    }
}