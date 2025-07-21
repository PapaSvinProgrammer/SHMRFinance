package com.example.updatetransaction.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.ui.navigation.UpdateTransactionRoute
import com.example.updatetransaction.di.DaggerUpdateTransactionComponent
import com.example.updatetransaction.presentation.UpdateTransactionScreen
import com.example.updatetransaction.presentation.UpdateTransactionViewModel

fun NavGraphBuilder.updateTransactionDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<UpdateTransactionRoute> {
        val route = it.toRoute<UpdateTransactionRoute>()
        val component = DaggerUpdateTransactionComponent
            .factory()
            .create(appComponent)

        val viewModel: UpdateTransactionViewModel = viewModel(
            factory = component.viewModelFactory
        )

        UpdateTransactionScreen(
            navController = navController,
            viewModel = viewModel,
            transactionId = route.id
        )
    }
}