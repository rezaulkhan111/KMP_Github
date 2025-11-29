package com.machinecode.kmp_github.model

import kotlinx.serialization.SerialName

data class RepositoryResponseDTO(
    @SerialName("total_count") val totalCount: Int? = null,
    @SerialName("incomplete_results") val incompleteResults: Boolean? = null,
    @SerialName("items") val items: MutableList<RepositoryDTO>? = null
) {
    fun mapData() {
        if (!items.isNullOrEmpty()) {
            items.forEach { itRep ->
                itRep.ownerId = itRep.owner?.id
            }
        }
    }
}

data class RepositoryDTO(
    @SerialName("id") val id: Int? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("owner") val owner: OwnerDTO? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("fork") val fork: Boolean? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("pushed_at") val pushedAt: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("stargazers_count") val stargazersCount: Int? = null,
    @SerialName("watchers_count") val watchersCount: Int? = null,
    @SerialName("forks_count") val forksCount: Int? = null,
    @SerialName("open_issues_count") val openIssuesCount: Int? = null,
    @SerialName("forks") val forks: Int? = null,
    @SerialName("open_issues") val openIssues: Int? = null,
    @SerialName("watchers") val watchers: Int? = null,
    @SerialName("score") val score: Double? = null,
    @SerialName("default_branch") val defaultBranch: String? = null,
    var ownerId: Int? = null,
)

data class OwnerDTO(
    @SerialName("login") val login: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("site_admin") val siteAdmin: Boolean? = null
)