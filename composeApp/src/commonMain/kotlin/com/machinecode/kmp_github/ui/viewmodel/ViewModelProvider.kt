package com.machinecode.kmp_github.ui.viewmodel

import com.machinecode.kmp_github.data.network.GithubApiService
import com.machinecode.kmp_github.data.repository.GithubRepository

object ViewModelProvider {
    fun provideGithubVM(): GithubVM {
//        val api = GithubApiService()
        val repo = GithubRepository(GithubApiService())
        return GithubVM(repo)
    }
}