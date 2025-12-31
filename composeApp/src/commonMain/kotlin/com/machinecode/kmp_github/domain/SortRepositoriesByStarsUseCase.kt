package com.machinecode.kmp_github.domain

import com.machinecode.kmp_github.domain.model.RepositoryDetails

class SortRepositoriesByStarsUseCase {
    operator fun invoke(
        list: List<RepositoryDetails>, isDescending: Boolean = true
    ): List<RepositoryDetails> {
        return if (isDescending) {
            list.sortedByDescending { it.stargazersCount }
//            list.sortedWith(compareBy(nullsFirst()) { it.stargazersCount }).reversed()
        } else {
            list.sortedBy { it.stargazersCount }
//            list.sortedWith(compareBy(nullsFirst()) { it.stargazersCount })
        }
    }
}