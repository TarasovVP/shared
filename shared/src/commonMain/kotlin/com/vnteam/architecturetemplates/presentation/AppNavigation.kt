package com.vnteam.architecturetemplates.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vnteam.architecturetemplates.presentation.details.DetailsScreen
import com.vnteam.architecturetemplates.presentation.list.ListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ListScreen {
            val forkId = it.toString()
            navController.navigate("details/$forkId")
        } }
        composable("details/{forkId}", arguments = listOf(navArgument("forkId") { type = NavType.StringType
            defaultValue = "" })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            DetailsScreen(forkId) {
                navController.popBackStack()
            }
        }
    }
}