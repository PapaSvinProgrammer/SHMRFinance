package com.example.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.articles.presentation.navigation.articleDestination
import com.example.bankaccountlist.presentation.navigation.bankAccountListDestination
import com.example.bankaccountscreen.presentation.navigation.bankAccountDestination
import com.example.corecomponent.AppComponent
import com.example.createbankaccount.presentation.navigation.createBankAccountDestination
import com.example.createtransaction.presentation.navigation.createTransactionDestination
import com.example.expenses.presentation.navigation.expensesDestination
import com.example.income.presentation.navigation.incomeDestination
import com.example.settings.presentation.navigation.settingsDestination
import com.example.splash.splashDestination
import com.example.synchronizationscreen.presentation.navigation.synchronizationDestination
import com.example.tranasctionanalysis.presentation.navigation.transactionAnalysisDestination
import com.example.transactionhistory.presentation.navigation.transactionHistoryDestination
import com.example.ui.navigation.NavRoute
import com.example.ui.navigation.SettingsRoute
import com.example.updatebankaccount.presentation.navigation.updateBankAccountDestination
import com.example.updatetransaction.presentation.navigation.updateTransactionDestination

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: NavRoute,
    appComponent: AppComponent
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SettingsRoute,
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
            appComponent = appComponent
        )

        incomeDestination(
            navController = navController,
            appComponent = appComponent
        )

        articleDestination(appComponent)

        createBankAccountDestination(
            navController = navController,
            appComponent = appComponent
        )

        settingsDestination(
            navController = navController,
            appComponent = appComponent
        )

        bankAccountListDestination(
            navController = navController,
            appComponent = appComponent
        )

        bankAccountDestination(
            navController = navController,
            appComponent = appComponent
        )

        transactionHistoryDestination(
            navController = navController,
            appComponent = appComponent
        )

        updateBankAccountDestination(
            navController = navController,
            appComponent = appComponent
        )

        createBankAccountDestination(
            navController = navController,
            appComponent = appComponent
        )

        updateTransactionDestination(
            navController = navController,
            appComponent = appComponent
        )

        transactionAnalysisDestination(
            navController = navController,
            appComponent = appComponent
        )

        createTransactionDestination(
            navController = navController,
            appComponent = appComponent
        )

        synchronizationDestination(
            navController = navController,
            appComponent = appComponent
        )
    }
}
