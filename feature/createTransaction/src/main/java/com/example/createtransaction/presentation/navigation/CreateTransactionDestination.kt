package com.example.createtransaction.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.createtransaction.di.DaggerCreateTransactionComponent
import com.example.createtransaction.presentation.CreateTransactionScreen
import com.example.createtransaction.presentation.CreateTransactionViewModel
import com.example.ui.navigation.CreateTransactionRoute

fun NavGraphBuilder.createTransactionDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<CreateTransactionRoute> {
        val route = it.toRoute<CreateTransactionRoute>()
        val component = DaggerCreateTransactionComponent
            .factory()
            .create(appComponent)

        val viewModel: CreateTransactionViewModel = viewModel(
            factory = component.viewModelFactory
        )

        CreateTransactionScreen(
            navController = navController,
            viewModel = viewModel,
            isIncome = route.isIncome
        )
    }
}