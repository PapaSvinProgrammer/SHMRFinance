package com.example.pincodescreen.presentation.navigatoin

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.pincodescreen.di.DaggerOtpComponent
import com.example.pincodescreen.presentation.OtpViewModel
import com.example.pincodescreen.presentation.CreateOtpScreen
import com.example.pincodescreen.presentation.DefaultOtpScreen
import com.example.pincodescreen.presentation.DisableOtpScreen
import com.example.ui.navigation.OtpRoute

fun NavGraphBuilder.otpDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<OtpRoute> {
        val component = remember {
            DaggerOtpComponent
                .factory()
                .create(appComponent)
        }

        val route = it.toRoute<OtpRoute>()

        val viewModel: OtpViewModel = viewModel(
            factory = component.viewModelFactory
        )

        if (route.isDisable) {
            DisableOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        else if (route.isCreate) {
            CreateOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        else {
            DefaultOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}