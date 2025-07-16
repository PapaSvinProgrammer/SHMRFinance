package com.example.transactionhistory.presentation.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.transactionhistory.di.DaggerTransactionComponent
import com.example.transactionhistory.presentation.TransactionHistoryScreen
import com.example.transactionhistory.presentation.TransactionHistoryViewModel
import com.example.ui.navigation.TransactionHistoryRoute

fun NavGraphBuilder.transactionHistoryDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<TransactionHistoryRoute> {
        val route = it.toRoute<TransactionHistoryRoute>()
        val component = DaggerTransactionComponent
            .factory()
            .create(
                context = LocalContext.current,
                appComponent = appComponent
            )

        val viewModel: TransactionHistoryViewModel = viewModel(
            factory = component.viewModelFactory
        )

        TransactionHistoryScreen(
            navController = navController,
            isIncome = route.isIncome,
            viewModel = viewModel
        )
    }
}