package com.example.oophandbook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.oophandbook.ui.screen.bookmark.BookmarkScreen
import com.example.oophandbook.ui.screen.home.HomeScreen
import com.example.oophandbook.ui.screen.setttings.SettingsScreen
import com.example.oophandbook.ui.screen.unit.UnitScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        route = "/",
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onNavigateToUnit = navController::navigateToUnitScreen)
        }
        composable("bookmarks") {
            BookmarkScreen(onNavigateToUnit = navController::navigateToUnitScreen)
        }
        /*composable("settings") {
            SettingsScreen()
        }*/

        composable(
            "unit/{unit_id}",
            arguments = listOf(navArgument("unit_id") { type = NavType.IntType })
        ) {
            UnitScreen(onNavigateToUnit = navController::navigateToUnitScreen)
        }
    }
}
