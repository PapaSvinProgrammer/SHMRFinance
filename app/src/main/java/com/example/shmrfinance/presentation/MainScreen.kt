package com.example.shmrfinance.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.NavigationGraph
import com.example.network.connectivityState.NetworkConnectionState
import com.example.network.connectivityState.rememberConnectivityState
import com.example.shmrfinance.appComponent
import com.example.shmrfinance.ui.R
import com.example.ui.navigation.BankAccountRoute
import com.example.ui.navigation.BottomNavigationBar
import com.example.ui.navigation.ExpensesRoute
import com.example.ui.navigation.IncomeRoute
import com.example.ui.navigation.NavRoute
import com.example.ui.navigation.OtpRoute
import com.example.ui.navigation.SplashRoute

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
            NetworkSnackbarHost(
                snackBarHostState = snackBarHostState,
                route = backStackEntry?.destination?.route
            )
        }
    ) { _ ->
        NavigationGraph(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = 80.dp),
            navController = navController,
            startRoute = startRoute,
            appComponent = context.appComponent
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        SplashRoute::class.java.canonicalName -> onResult(false)
        "${OtpRoute::class.java.canonicalName}/{isCreate}/{isDisable}" -> onResult(false)
        else -> onResult(true)
    }
}

@Composable
private fun NetworkSnackbarHost(
    snackBarHostState: SnackbarHostState,
    route: String?
) {
    val padding = when (route) {
        IncomeRoute::class.java.canonicalName -> 70.dp
        ExpensesRoute::class.java.canonicalName -> 70.dp
        BankAccountRoute::class.java.canonicalName -> 70.dp
        else -> 0.dp
    }

    val animatedPadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(durationMillis = 300)
    )

    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier.padding(bottom = animatedPadding)
    )
}
