package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.articles.presentation.navigation.articleDestination
import com.example.bankaccountlist.presentation.navigation.bankAccountListDestination
import com.example.bankaccountscreen.presentation.navigation.bankAccountDestination
import com.example.createbankaccount.presentation.navigation.createBankAccountDestination
import com.example.createtransaction.presentation.navigation.createTransactionDestination
import com.example.expenses.presentation.navigation.expensesDestination
import com.example.income.presentation.navigation.incomeDestination
import com.example.settings.settingsDestination
import com.example.shmrfinance.appComponent
import com.example.splash.splashDestination
import com.example.synchronizationscreen.presentation.navigation.synchronizationDestination
import com.example.tranasctionanalysis.presentation.navigation.transactionAnalysisDestination
import com.example.transactionhistory.presentation.navigation.transactionHistoryDestination
import com.example.ui.navigation.BankAccountRoute
import com.example.ui.navigation.NavRoute
import com.example.ui.navigation.SplashRoute
import com.example.ui.navigation.TransactionAnalysisRoute
import com.example.updatebankaccount.presentation.navigation.updateBankAccountDestination
import com.example.updatetransaction.presentation.navigation.updateTransactionDestination

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: NavRoute
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TransactionAnalysisRoute(isIncome = true),
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
        splashDestination(navController)

        expensesDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        incomeDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        articleDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        createBankAccountDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        settingsDestination(
            navController = navController
        )

        bankAccountListDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        bankAccountDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        transactionHistoryDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        updateBankAccountDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        createBankAccountDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        updateTransactionDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        transactionAnalysisDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        createTransactionDestination(
            navController = navController,
            appComponent = context.appComponent
        )

        synchronizationDestination(
            navController = navController,
            appComponent = context.appComponent
        )
    }
}
