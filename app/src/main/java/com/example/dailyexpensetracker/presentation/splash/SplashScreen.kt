package com.example.dailyexpensetracker.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dailyexpensetracker.ExpenseDashboardScreen
import com.example.dailyexpensetracker.presentation.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel() // Hilt will provide it here
) {
    val destination by viewModel.startDestination.collectAsState()

    LaunchedEffect(destination) {
        if (destination != Screen.Splash) {
            navController.navigate(destination.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7F3DFF)), // Purple background
        contentAlignment = Alignment.Center
    ) {
        // Light Aura Effect (behind text)
        Box(
            modifier = Modifier
                .size(280.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFF5B97).copy(alpha = 0.5f),
                            Color.Transparent
                        ),
                        center = Offset(0f, 0f),
                        radius = 400f
                    ),
                    shape = CircleShape
                )
        )

        // Glowing Logo Text
        GradientGlowingText(text = "MyExpense")
    }
}
/*
@Composable
fun AppEntryPoint(navController: NavHostController) {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        ExpenseDashboardScreen() // Replace with your actual home screen
    }
}*/
