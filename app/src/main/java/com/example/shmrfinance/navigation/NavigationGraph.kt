package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
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
import com.example.localviewmodelfactory.LocalViewModelFactory
import com.example.navigationroute.ArticlesRoute
import com.example.navigationroute.BankAccountListScreenRoute
import com.example.navigationroute.BankAccountRoute
import com.example.navigationroute.CreateBankAccountRoute
import com.example.navigationroute.ExpensesRoute
import com.example.navigationroute.IncomeRoute
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SettingsRoute
import com.example.navigationroute.SplashRoute
import com.example.navigationroute.TransactionHistoryRoute
import com.example.navigationroute.UpdateBankAccountRoute
import com.example.transactionhistory.TransactionHistoryScreen
import com.example.updatebankaccount.UpdateBankAccountScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    startRoute: NavRoute
) {
    CompositionLocalProvider(LocalViewModelFactory provides viewModelFactory) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = BankAccountRoute,
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
            composable<SplashRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                SplashScreen(navController = navController)
            }

            composable<ExpensesRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                ExpensesScreen(navController)
            }

            composable<IncomeRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                IncomeScreen(navController)
            }

            composable<BankAccountRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                BankAccountScreen(navController)
            }

            composable<ArticlesRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                ArticlesScreen(navController)
            }

            composable<SettingsRoute>(
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
                popEnterTransition = { fadeIn(animationSpec = tween(500)) },
                popExitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                SettingsScreen()
            }

            composable<CreateBankAccountRoute> {
                CreateBankAccountScreen(navController)
            }

            composable<BankAccountListScreenRoute> {
                BankAccountListScreen(navController)
            }

            composable<TransactionHistoryRoute> {
                val route = it.toRoute<TransactionHistoryRoute>()

                TransactionHistoryScreen(
                    navController = navController,
                    isIncome = route.isIncome
                )
            }

            composable<UpdateBankAccountRoute> {
                val route = it.toRoute<UpdateBankAccountRoute>()

                UpdateBankAccountScreen(
                    navController = navController,
                    bankAccountId = route.id
                )
            }
        }
    }
}