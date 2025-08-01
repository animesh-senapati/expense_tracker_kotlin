package com.example.dailyexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.dailyexpensetracker.presentation.navigation.AppNavGraph
import com.example.dailyexpensetracker.presentation.splash.SplashScreen
import com.example.dailyexpensetracker.presentation.splash.SplashViewModel
import com.example.dailyexpensetracker.ui.theme.DailyExpenseTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            DailyExpenseTrackerTheme {
                val navController = rememberNavController()

                // SplashScreen will internally call hiltViewModel()
                Box(modifier = Modifier.fillMaxSize()) {
                   // SplashScreen(navController = navController)
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}




/*
val navController = rememberNavController()
AppEntryPoint(navController)*/
