package com.example.shmrfinance.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ui.navigation.ExpensesRoute
import com.example.ui.theme.SHMRFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        enableEdgeToEdge()

        setContent {
            SHMRFinanceTheme {
                MainScreen(
                    startRoute = ExpensesRoute
                )
            }
        }
    }
}