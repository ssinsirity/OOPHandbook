package com.example.oophandbook.ui.navigation.bottom_bar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.oophandbook.R

enum class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(route = "home", title = R.string.home, icon = Icons.Default.Home),
    BOOKMARKS(route = "bookmarks", title = R.string.bookmarks, icon = Icons.Default.FavoriteBorder),
    //SETTINGS(route = "settings", title = R.string.settings, icon = Icons.Default.Settings)
}