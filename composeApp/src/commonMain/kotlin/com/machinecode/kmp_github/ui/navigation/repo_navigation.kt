package com.machinecode.kmp_github.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.machinecode.kmp_github.ui.DetailsScreen
import com.machinecode.kmp_github.ui.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
//@Preview(showSystemUi = true)
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list_screen"
    ) {
        composable("list_screen") {
            // List screen
            HomeScreen(onRepoClick = { repo ->
                navController.navigate("details_screen/${repo.id.toString()}")
            })
        }

        composable("details_screen/{repoId}") { backStackEntry ->
            val repoId = backStackEntry.arguments?.getString("repoId")
            DetailsScreen(repoId = repoId)
        }
    }
}