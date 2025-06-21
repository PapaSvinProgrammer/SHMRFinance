package com.example.shmrfinance.presentation

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.navigationroute.ExpensesRoute
import com.example.ui.theme.SHMRFinanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        enableEdgeToEdge()

        setContent {
            SHMRFinanceTheme {
                MainScreen(
                    startRoute = com.example.navigationroute.ExpensesRoute
                )
            }
        }
    }
}