package com.anucodes.lokal_assessment.BottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Jobs: BottomNavigationItems("Jobs", Icons.Default.Home, "Home")
    object Bookmark: BottomNavigationItems("Bookmark", Icons.Default.Bookmark, "Bookmarks")
}