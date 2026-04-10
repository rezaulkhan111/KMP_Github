package com.machinecode.kmp_github.ui.navigation

//@Composable
////@Preview(showSystemUi = true)
//fun NavHostScreen() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "list_screen"
//    ) {
//        composable("list_screen") {
//            // List screen
//            HomeScreen(onRepoClick = { repo ->
//                navController.navigate("details_screen/${repo.id.toString()}")
//            })
//        }
//
//        composable("details_screen/{repoId}") { backStackEntry ->
//            val repoId = backStackEntry.arguments?.getString("repoId")
//            DetailsScreen(repoId = repoId)
//        }
//    }
//}