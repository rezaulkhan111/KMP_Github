package com.machinecode.kmp_github.data.db

data class RepositoryEntity(
    /*  @PrimaryKey*/ val repositoryTableId: Int,
    val nodeId: String? = null,
    val name: String? = null,
    val fullName: String? = null,
    val description: String? = null,
//    val fork: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val language: String? = null,
//    val pushedAt: String? = null,
    val stargazersCount: Int? = null,
    val forksCount: Int? = null,
    val defaultBranch: String? = null,
    val ownerId: Int
)