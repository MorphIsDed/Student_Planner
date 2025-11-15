package com.example.studentplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.studentplanner.ui.navigation.AppNavigation
import com.example.studentplanner.ui.theme.StudentPlannerTheme
import com.example.studentplanner.ui.view.SplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLoading by remember { mutableStateOf(true) }
            var darkTheme by remember { mutableStateOf(false) }
            var notificationsEnabled by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(1000) // 1 second delay
                isLoading = false
            }

            StudentPlannerTheme(darkTheme = darkTheme) {
                if (isLoading) {
                    SplashScreen()
                } else {
                    AppNavigation(
                        darkTheme = darkTheme,
                        onThemeChange = { darkTheme = it },
                        notificationsEnabled = notificationsEnabled,
                        onNotificationsChange = { notificationsEnabled = it }
                    )
                }
            }
        }
    }
}