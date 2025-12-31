package com.machinecode.kmp_github.domain.mapper

import com.machinecode.kmp_github.data.db.RepoWithOwner
import com.machinecode.kmp_github.data.model.RepositoryDTO
import com.machinecode.kmp_github.domain.model.OwnerDetails
import com.machinecode.kmp_github.domain.model.RepositoryDetails
import com.machinecode.kmp_github.utils.formatCountDynamic
import com.machinecode.kmp_github.utils.formatDate
import com.machinecode.kmp_github.utils.getLanguageColor

fun RepoWithOwner.toDomainModel(): RepositoryDetails {
    return RepositoryDetails(
        id = repository.repositoryTableId,
        fullName = repository.fullName?.takeIf { it.isNotEmpty() } ?: "",
        name = repository.name?.takeIf { it.isNotEmpty() } ?: "",
        description = repository.description?.takeIf { it.isNotEmpty() } ?: "",
        updatedAt = formatDate(repository.updatedAt?.takeIf { it.isNotEmpty() } ?: "",
            "yyyy-MM-dd'T'HH:mm:ss",
            "MM-dd-yy hh:mm"),
        stargazersCount = repository.stargazersCount ?: 0,
        stargazersCountStr = formatCountDynamic(
            if (repository.stargazersCount != null && repository.stargazersCount != 0) {
                repository.stargazersCount
            } else {
                0
            }
        ),
        forksCount = repository.forksCount ?: 0,
        defaultBranch = repository.defaultBranch?.takeIf { it.isNotEmpty() } ?: "",
        language = repository.language?.takeIf { it.isNotEmpty() } ?: "",
        languageColor = if (!repository.language.isNullOrEmpty()) {
            getLanguageColor(repository.language)
        } else {
            null
        },
        owner = OwnerDetails(
            id = owner.ownerTableId,
            login = owner.login?.takeIf { it.isNotEmpty() } ?: "",
            avatarUrl = owner.avatarUrl?.takeIf { it.isNotEmpty() } ?: "")
    )
}

fun RepositoryDTO.toDomainModel(): RepositoryDetails {
    val owner = this.owner
    return RepositoryDetails(
        id = id ?: 0,
        fullName = fullName?.takeIf { it.isNotEmpty() } ?: "",
        name = name?.takeIf { it.isNotEmpty() } ?: "",
        description = description?.takeIf { it.isNotEmpty() } ?: "",
        updatedAt = formatDate(
            updatedAt?.takeIf { it.isNotEmpty() } ?: "",
            "yyyy-MM-dd'T'HH:mm:ss",
            "MM-dd-yy hh:mm"
        ),
        stargazersCount = stargazersCount ?: 0,
        stargazersCountStr = formatCountDynamic(
            stargazersCount ?: 0
        ),
        forksCount = forksCount ?: 0,
        defaultBranch = defaultBranch?.takeIf { it.isNotEmpty() } ?: "",
        language = language?.takeIf { it.isNotEmpty() } ?: "",
        languageColor = language?.takeIf { it.isNotEmpty() }?.let { getLanguageColor(it) },
        owner = OwnerDetails(
            id = if (owner?.id != null) {
                owner.id
            } else {
                0
            },
            login = owner?.login?.takeIf { it.isNotEmpty() } ?: "",
            avatarUrl = owner?.avatarUrl?.takeIf { it.isNotEmpty() } ?: ""
        )
    )
}