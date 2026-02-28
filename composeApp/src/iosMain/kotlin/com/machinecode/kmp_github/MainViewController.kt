package com.machinecode.kmp_github

import androidx.compose.ui.window.ComposeUIViewController
import com.machinecode.kmp_github.ui.HomeScreen
import com.machinecode.kmp_github.ui.navigation.NavHostScreen

fun MainViewController() = ComposeUIViewController {
    NavHostScreen()
}