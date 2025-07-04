package com.example.shmrfinance.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.connectivitystate.NetworkConnectionState
import com.example.connectivitystate.rememberConnectivityState
import com.example.navigationroute.NavRoute
import com.example.shmrfinance.app.R
import com.example.shmrfinance.navigation.NavigationGraph
import com.example.shmrfinance.navigation.BottomNavigationBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    startRoute: NavRoute
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val connectivityState by rememberConnectivityState()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(connectivityState) {
        if (connectivityState == NetworkConnectionState.Failure) {
            snackBarHostState.showSnackbar(
                message = context.getString(R.string.no_internet),
                duration = SnackbarDuration.Indefinite
            )
        }
    }

    LaunchedEffect(backStackEntry) {
        val currentRoute = backStackEntry?.destination?.route

        bottomBarIsVisibility(
            route = currentRoute,
            onResult = { bottomBarVisible = it }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                visible = bottomBarVisible
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->
        NavigationGraph(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startRoute = startRoute
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        com.example.navigationroute.SplashRoute::class.java.canonicalName -> onResult(false)
        else -> onResult(true)
    }
}