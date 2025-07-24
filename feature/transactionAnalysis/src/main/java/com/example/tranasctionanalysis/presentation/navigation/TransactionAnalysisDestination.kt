package com.example.tranasctionanalysis.presentation.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.tranasctionanalysis.di.DaggerTransactionAnalysisComponent
import com.example.tranasctionanalysis.presentation.TransactionAnalysisScreen
import com.example.tranasctionanalysis.presentation.TransactionAnalysisViewModel
import com.example.ui.navigation.TransactionAnalysisRoute

fun NavGraphBuilder.transactionAnalysisDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<TransactionAnalysisRoute> {
        val component = remember {
            DaggerTransactionAnalysisComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: TransactionAnalysisViewModel = viewModel(
            factory = component.viewModelFactory
        )

        val route = it.toRoute<TransactionAnalysisRoute>()

        TransactionAnalysisScreen(
            navController = navController,
            viewModel = viewModel,
            isIncome = route.isIncome
        )
    }
}