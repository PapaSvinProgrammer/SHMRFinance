package com.example.shmrfinance.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shmrfinance.ui.screens.ArticlesScreen
import com.example.shmrfinance.ui.screens.BankAccountScreen
import com.example.shmrfinance.ui.screens.ExpensesScreen
import com.example.shmrfinance.ui.screens.IncomeScreen
import com.example.shmrfinance.ui.screens.SettingsScreen
import com.example.shmrfinance.ui.screens.SplashScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: NavRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ExpensesRoute,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<SplashRoute> {
            SplashScreen()
        }

        composable<ExpensesRoute> {
            ExpensesScreen()
        }

        composable<IncomeRoute> {
            IncomeScreen()
        }

        composable<BankAccountRoute> {
            BankAccountScreen()
        }

        composable<ArticlesRoute> {
            ArticlesScreen()
        }

        composable<SettingsRoute> {
            SettingsScreen()
        }
    }
}