package com.anucodes.lokal_assessment.BottomNavigation

import android.view.RoundedCorner
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val navItems = listOf(
        BottomNavigationItems.Jobs,
        BottomNavigationItems.Bookmark
    )

    NavigationBar(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ),
        containerColor = Color.Gray,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { items->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = items.icon,
                        contentDescription = items.label
                    )
                },
                label = {
                    Text(
                        text = items.label,
                        fontSize = 16.sp
                    )
                },
                alwaysShowLabel = false,
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}