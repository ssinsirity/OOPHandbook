package com.example.oophandbook.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.oophandbook.R
import com.example.oophandbook.ui.navigation.NavGraph
import com.example.oophandbook.ui.navigation.bottom_bar.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean = false,
    onToggleTheme: () -> Unit = {}
) {
    val navController = rememberNavController()
    val bottomItems = BottomNavItem.values().toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.java_handbook)) },
                actions = {
                    TextButton(onClick = onToggleTheme) {
                        Text(
                            if (isDarkTheme) "☀\uFE0F" else "\uD83C\uDF18",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                items = bottomItems,
                navController = navController
            )
        }
    ) {
        NavGraph(
            navController = navController, modifier = Modifier
                .padding(it)
                .padding(8.dp)
        )
    }
}

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = items.any { it.route == currentDestination?.route }


    if (bottomBarDestination) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    label = { Text(text = stringResource(item.title)) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}