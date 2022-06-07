package com.example.gifter_single_module.components.bottom_navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.selects.select

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(modifier = modifier) {
        Row() {
            items.map() {
                BottomNavigationItem(
                    selected = currentRoute == it.route,
                    onClick = { navController.navigate(it.route) },
                    label = { Text(text = it.name) },
                    alwaysShowLabel = it.selected,
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = "Navigate to ${it.name}"
                        )
                    }
                )
            }
        }
    }
}