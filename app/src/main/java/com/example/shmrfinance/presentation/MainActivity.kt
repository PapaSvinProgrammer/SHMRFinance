package com.example.shmrfinance.presentation

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import com.example.navigationroute.ExpensesRoute
import com.example.shmrfinance.appComponent
import com.example.ui.theme.SHMRFinanceTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        enableEdgeToEdge()

        appComponent.inject(this)

        setContent {
            SHMRFinanceTheme {
                MainScreen(
                    startRoute = ExpensesRoute,
                    viewModelFactory = remember { appComponent.viewModelFactory() }
                )
            }
        }
    }
}