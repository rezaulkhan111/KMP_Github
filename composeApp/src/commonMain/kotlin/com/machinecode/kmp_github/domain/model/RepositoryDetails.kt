package com.machinecode.kmp_github.domain.model

import androidx.compose.ui.graphics.Color

data class RepositoryDetails(
    val id: Int,
    var fullName: String,
    var name: String,
    var description: String,
    var updatedAt: String,
    var language: String,
    val languageColor: Color? = null,
    val stargazersCount: Int,
    val stargazersCountStr: String,
    val forksCount: Int,
    val defaultBranch: String,
    var owner: OwnerDetails
)