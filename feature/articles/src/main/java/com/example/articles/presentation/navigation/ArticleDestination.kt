package com.example.articles.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.articles.di.DaggerArticlesComponent
import com.example.articles.presentation.ArticlesScreen
import com.example.articles.presentation.ArticlesViewModel
import com.example.corecomponent.AppComponent
import com.example.ui.navigation.ArticlesRoute

fun NavGraphBuilder.articleDestination(
    appComponent: AppComponent,
    navController: NavController
) {
    composable<ArticlesRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val component = remember {
            DaggerArticlesComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: ArticlesViewModel = viewModel(factory = component.viewModelFactory)

        ArticlesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}