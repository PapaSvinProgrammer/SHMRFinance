package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.articles.ArticlesScreen
import com.example.articles.di.DaggerArticlesComponent
import com.example.bankaccountlist.BankAccountListScreen
import com.example.bankaccountlist.di.DaggerBankAccountListComponent
import com.example.bankaccountscreen.BankAccountScreen
import com.example.bankaccountscreen.di.DaggerBankAccountComponent
import com.example.createbankaccount.CreateBankAccountScreen
import com.example.createbankaccount.di.DaggerCreateBankAccountComponent
import com.example.expenses.ExpensesScreen
import com.example.expenses.di.DaggerExpensesComponent
import com.example.income.IncomeScreen
import com.example.income.di.DaggerIncomeComponent
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
import com.example.settings.SettingsScreen
import com.example.splash.SplashScreen
import com.example.transactionhistory.TransactionHistoryScreen
import com.example.transactionhistory.di.DaggerTransactionComponent
import com.example.updatebankaccount.UpdateBankAccountScreen
import com.example.updatebankaccount.di.DaggerUpdateBankAccountComponent

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
            val component = DaggerExpensesComponent.factory().create(LocalContext.current)
            ExpensesScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<IncomeRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerIncomeComponent.factory().create(LocalContext.current)
            IncomeScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<BankAccountRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerBankAccountComponent.factory().create(LocalContext.current)
            BankAccountScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<ArticlesRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerArticlesComponent.factory().create(LocalContext.current)
            ArticlesScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
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
            val component = DaggerCreateBankAccountComponent.factory().create(LocalContext.current)
            CreateBankAccountScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<BankAccountListScreenRoute> {
            val component = DaggerBankAccountListComponent.factory().create(LocalContext.current)
            BankAccountListScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<TransactionHistoryRoute> {
            val route = it.toRoute<TransactionHistoryRoute>()
            val component = DaggerTransactionComponent.factory().create(LocalContext.current)

            TransactionHistoryScreen(
                navController = navController,
                isIncome = route.isIncome,
                viewModel = remember { component.viewModel }
            )
        }

        composable<UpdateBankAccountRoute> {
            val route = it.toRoute<UpdateBankAccountRoute>()
            val component = DaggerUpdateBankAccountComponent.factory().create()

            UpdateBankAccountScreen(
                navController = navController,
                bankAccountId = route.id,
                viewModel = remember { component.viewModel }
            )
        }
    }
}