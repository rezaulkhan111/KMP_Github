package com.machinecode.kmp_github.di

import com.machinecode.kmp_github.data.network.GithubApiService
import com.machinecode.kmp_github.data.repository.GithubRepository
import com.machinecode.kmp_github.ui.viewmodel.GithubVM
import org.koin.dsl.module

val commonModule = module {

    single { GithubApiService() }

    single { GithubRepository(get()) }

    factory { GithubVM(get()) }
}