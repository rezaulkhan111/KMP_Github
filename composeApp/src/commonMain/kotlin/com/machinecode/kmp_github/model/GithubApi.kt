package com.machinecode.kmp_github.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    val name: String? = null,
    val followers: Int? = null,
    val following: Int? = null
)