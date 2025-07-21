package com.example.synchronizationscreen.presentation.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.synchronizationscreen.di.DaggerSynchronizationComponent
import com.example.synchronizationscreen.presentation.SynchronizationScreen
import com.example.synchronizationscreen.presentation.SynchronizationViewModel
import com.example.ui.navigation.SynchronizationRoute

fun NavGraphBuilder.synchronizationDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<SynchronizationRoute> {
        val component = remember {
            DaggerSynchronizationComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: SynchronizationViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SynchronizationScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}