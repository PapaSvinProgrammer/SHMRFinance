package com.example.shmrfinance.presentation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.corecomponent.AppViewModel
import com.example.shmrfinance.appComponent
import com.example.ui.navigation.ExpensesRoute
import com.example.ui.theme.SHMRFinanceTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: AppViewModel

    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = appComponent.viewModel
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        enableEdgeToEdge()

        setContent {
            val isDarkTheme by viewModel.darkTheme.collectAsStateWithLifecycle()
            val currentColor by viewModel.currentColor.collectAsStateWithLifecycle()

            SHMRFinanceTheme(
                darkTheme = isDarkTheme,
                currentColor = Color(currentColor)
            ) {
                val context = LocalContext.current as ComponentActivity

                DisposableEffect(isDarkTheme) {
                    context.enableEdgeToEdge(
                        statusBarStyle = getSystemBarStyle(isDarkTheme),
                        navigationBarStyle = getSystemBarStyle(isDarkTheme)
                    )

                    onDispose {  }
                }

                MainScreen(startRoute = ExpensesRoute)
            }
        }
    }
}

private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
    return when (isDark) {
        true -> SystemBarStyle.dark(Color.Transparent.toArgb())
        false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
    }
}