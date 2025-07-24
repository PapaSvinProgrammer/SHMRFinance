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
import com.example.ui.navigation.OtpRoute
import com.example.ui.navigation.SplashRoute
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
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            if (uiState.currentColor == AppViewModel.DEFAULT_COLOR) return@setContent

            SHMRFinanceTheme(
                darkTheme = uiState.darkTheme,
                currentColor = Color(uiState.currentColor)
            ) {
                val context = LocalContext.current as ComponentActivity

                DisposableEffect(uiState.darkTheme) {
                    context.enableEdgeToEdge(
                        statusBarStyle = getSystemBarStyle(uiState.darkTheme),
                        navigationBarStyle = getSystemBarStyle(uiState.darkTheme)
                    )

                    onDispose {  }
                }

                val startRoute = if (uiState.authState)
                    OtpRoute(isCreate = false, isDisable = false)
                else
                    ExpensesRoute

                MainScreen(
                    startRoute = SplashRoute(startRoute is OtpRoute),
                    hapticNumber = uiState.hapticNumber
                )
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
