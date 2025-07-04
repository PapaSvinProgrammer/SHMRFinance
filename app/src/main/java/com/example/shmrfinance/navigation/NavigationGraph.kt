package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigationroute.ArticlesRoute
import com.example.navigationroute.NavRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: NavRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ArticlesRoute,
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
//        composable<SplashRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            SplashScreen(navController = navController)
//        }
//
//        composable<ExpensesRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            ExpensesScreen(navController)
//        }
//
//        composable<IncomeRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            IncomeScreen(navController)
//        }
//
//        composable<BankAccountRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            BankAccountScreen(navController)
//        }
//
//        composable<ArticlesRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            ArticlesScreen(navController)
//        }
//
//        composable<SettingsRoute>(
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) },
//            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
//            popExitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            SettingsScreen()
//        }
//
//        composable<CreateBankAccountRoute> {
//            CreateBankAccountScreen(navController)
//        }
//
//        composable<BankAccountListScreenRoute> {
//            BankAccountListScreen(navController)
//        }
//
//        composable<TransactionHistoryRoute> {
//            val route = it.toRoute<TransactionHistoryRoute>()
//
//            TransactionHistoryScreen(
//                navController = navController,
//                isIncome = route.isIncome
//            )
//        }
//
//        composable<UpdateBankAccountRoute> {
//            val route = it.toRoute<UpdateBankAccountRoute>()
//
//            UpdateBankAccountScreen(
//                navController = navController,
//                bankAccountId = route.id
//            )
//        }
    }
}