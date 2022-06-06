package com.example.gifter_single_module.components.bottom_navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavigationItem) -> Unit
) {
    BottomNavigation(modifier = modifier) {
        Row() {
            items.forEach() {
                BottomNavigationItem(
                    selected = it.selected,
                    onClick = { onItemClick(it) },
                    label = { Text(text = it.name) },
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