package com.machinecode.kmp_github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.machinecode.kmp_github.ui.navigation.NavHostScreen
import com.machinecode.kmp_github.ui.theme.AndroidRepositoriesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidRepositoriesTheme {
                NavHostScreen()
            }
        }
    }
}