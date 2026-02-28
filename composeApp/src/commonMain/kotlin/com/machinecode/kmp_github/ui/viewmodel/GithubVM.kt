package com.machinecode.kmp_github.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.machinecode.kmp_github.data.repository.GithubRepository
import com.machinecode.kmp_github.domain.SortRepositoriesByStarsUseCase
import com.machinecode.kmp_github.domain.mapper.toDomainModel
import com.machinecode.kmp_github.domain.model.RepositoryDetails
import com.machinecode.kmp_github.utils.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GithubVM(private val repo: GithubRepository) : ViewModel() {
    private var currentPage = 1
    private var mPerPageItem = 10
    private var isLoading = false
    var isDescending = true

    private val _sortUseCase = SortRepositoriesByStarsUseCase()
    private val _fetchStatus = MutableStateFlow<Boolean?>(null)
    private val _repositories = MutableStateFlow<List<RepositoryDetails>>(emptyList())
    private val _canFetchMessage = MutableStateFlow<String?>(null)
    private val _searchQuery = MutableStateFlow("")
    private val _repositoryDetails = MutableStateFlow<RepositoryDetails?>(null)

    val searchQuery: StateFlow<String> = _searchQuery
    val repositories: StateFlow<List<RepositoryDetails>> = _repositories
    val fetchStatus: StateFlow<Boolean?> = _fetchStatus
    val messageFetch: StateFlow<String?> = _canFetchMessage
    val repositoryDetails: StateFlow<RepositoryDetails?> = _repositoryDetails

    private fun resetPagination() {
        currentPage = 1
        _repositories.value = emptyList()
    }

    fun fetchIfNeeded(query: String) {
        viewModelScope.launch {
            when (val result = repo.fetchRepository(query, currentPage, mPerPageItem)) {
                is ApiResult.Success -> {
                    val mappedData = result.data.items?.map { it.toDomainModel() } ?: emptyList()
                    _repositories.value = mappedData

                    currentPage++
                    _fetchStatus.value = true
                }

                is ApiResult.Error -> {
                    _canFetchMessage.value = "Error: ${result.exception.message}"
                    _fetchStatus.value = false
                }
            }
        }
    }

    fun fetchRepositories(query: String? = null) {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                if (!query.isNullOrEmpty()) {
                    when (val result = repo.fetchRepository(query, currentPage, mPerPageItem)) {
                        is ApiResult.Success -> {
                            val mappedData =
                                result.data.items?.map { it.toDomainModel() } ?: emptyList()

                            _repositories.value += mappedData
                            _fetchStatus.value = true
                            currentPage++
                        }

                        is ApiResult.Error -> {
                            _canFetchMessage.value = "Error: ${result.exception.message}"
                            _fetchStatus.value = false
                        }
                    }
                }
            } finally {
                isLoading = false
            }
        }
    }

    private fun sortRepositories(descending: Boolean = true) {
        val sorted = _sortUseCase(_repositories.value, descending)
        _repositories.value = sorted
    }

    fun toggleSortByStars() {
        isDescending = !isDescending
        sortRepositories(descending = isDescending)
    }

    fun clearCanFetchMessage() {
        _canFetchMessage.value = null
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        filterRepositories()
    }

    private fun filterRepositories() {
        val query = _searchQuery.value.lowercase()
        val original = repositories.value
        _repositories.value = if (query.isBlank()) original else original.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun onSearchClick() {
        if (searchQuery.value.isNotBlank()) {
            resetPagination()
            fetchIfNeeded(searchQuery.value)
        }
    }

    fun getRepositoryById(repoId: Int?) {
        if (repoId != null) {
            viewModelScope.launch {
                _repositoryDetails.value =
                    _repositories.value.find { repoOwner -> repoOwner.id == repoId }
            }
        }
    }
}