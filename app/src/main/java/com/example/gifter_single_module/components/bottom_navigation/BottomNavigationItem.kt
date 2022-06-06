package com.example.gifter_single_module.components.bottom_navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(val name: String, val route: String, val icon: ImageVector, var selected: Boolean)