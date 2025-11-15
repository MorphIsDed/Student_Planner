package com.example.studentplanner.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Tasks : BottomNavItem("tasks", Icons.Default.List, "Tasks")
    object Planner : BottomNavItem("planner", Icons.Default.DateRange, "Planner")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
}