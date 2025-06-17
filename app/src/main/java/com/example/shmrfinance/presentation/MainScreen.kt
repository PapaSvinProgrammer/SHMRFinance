package com.example.shmrfinance.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmrfinance.app.navigation.NavRoute
import com.example.shmrfinance.app.navigation.NavigationGraph
import com.example.shmrfinance.app.navigation.SplashRoute
import com.example.shmrfinance.ui.widget.components.BottomNavigationBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    startRoute: NavRoute
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

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
        SplashRoute::class.java.canonicalName -> onResult(false)
        else -> onResult(true)
    }
}