package com.machinecode.kmp_github

import androidx.compose.ui.window.ComposeUIViewController
import com.machinecode.kmp_github.di.initKoinPlatform
import com.machinecode.kmp_github.ui.HomeScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    HomeScreen()
}.also {
    initKoinPlatform()
}