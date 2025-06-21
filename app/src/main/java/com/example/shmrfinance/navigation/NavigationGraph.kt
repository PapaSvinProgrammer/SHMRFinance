package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.articles.ArticlesScreen
import com.example.createbankaccount.CreateBankAccountScreen
import com.example.expenses.ExpensesScreen
import com.example.income.IncomeScreen
import com.example.settings.SettingsScreen
import com.example.splash.SplashScreen
import com.example.bankaccountlist.BankAccountListScreen
import com.example.bankaccountscreen.BankAccountScreen
import com.example.transactionhistory.TransactionHistoryScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: com.example.navigationroute.NavRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = com.example.navigationroute.TransactionHistoryRoute(isIncome = true),
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
        composable<com.example.navigationroute.SplashRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            SplashScreen(navController = navController)
        }

        composable<com.example.navigationroute.ExpensesRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            ExpensesScreen(navController)
        }

        composable<com.example.navigationroute.IncomeRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            IncomeScreen(navController)
        }

        composable<com.example.navigationroute.BankAccountRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            BankAccountScreen(navController)
        }

        composable<com.example.navigationroute.ArticlesRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            ArticlesScreen(navController)
        }

        composable<com.example.navigationroute.SettingsRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            SettingsScreen()
        }

        composable<com.example.navigationroute.CreateBankAccountRoute> {
            CreateBankAccountScreen(navController)
        }

        composable<com.example.navigationroute.BankAccountListScreenRoute> {
            BankAccountListScreen(navController)
        }

        composable<com.example.navigationroute.TransactionHistoryRoute> {
            val route = it.toRoute<com.example.navigationroute.TransactionHistoryRoute>()

            TransactionHistoryScreen(
                navController = navController,
                isIncome = route.isIncome
            )
        }
    }
}