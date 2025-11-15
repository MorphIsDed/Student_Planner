package com.example.studentplanner.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studentplanner.ui.view.HomeScreen
import com.example.studentplanner.ui.view.PlannerScreen
import com.example.studentplanner.ui.view.SettingsScreen
import com.example.studentplanner.ui.view.TaskDetailScreen
import com.example.studentplanner.ui.view.TasksScreen

@Composable
fun AppNavigation(
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    onExitApp: () -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Tasks,
        BottomNavItem.Planner,
        BottomNavItem.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)) }
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(onTaskClick = { taskName -> navController.navigate("task_detail/$taskName") }) }
            composable(BottomNavItem.Tasks.route) { TasksScreen(onTaskClick = { taskName -> navController.navigate("task_detail/$taskName") }) }
            composable(BottomNavItem.Planner.route) { PlannerScreen() }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen(
                    darkTheme = darkTheme,
                    onThemeChange = onThemeChange,
                    notificationsEnabled = notificationsEnabled,
                    onNotificationsChange = onNotificationsChange,
                    onExitApp = onExitApp
                )
            }
            composable(
                "task_detail/{taskName}",
                arguments = listOf(navArgument("taskName") { type = NavType.StringType })
            ) {
                TaskDetailScreen(taskName = it.arguments?.getString("taskName"))
            }
        }
    }
}
