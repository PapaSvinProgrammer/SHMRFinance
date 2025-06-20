package com.example.shmrfinance.presentation.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.Category
import com.example.shmrfinance.R
import com.example.shmrfinance.ui.uiState.CategoryUIState
import com.example.shmrfinance.ui.widget.components.BasicLoadingScreen
import com.example.shmrfinance.ui.widget.components.CustomSearchBar
import com.example.shmrfinance.ui.widget.listItems.CategoryListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    navController: NavController,
    viewModel: ArticlesViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val categoryState by viewModel.categoryState.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.query) {
        if (query.isNotEmpty()) {
            viewModel.searchCategory()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_articles))
                }
            )
        }
    ) { innerPadding ->
        when (val state = categoryState) {
            is CategoryUIState.Error -> {}
            CategoryUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is CategoryUIState.Success -> {
                var categoryList = state.data

                if (query.isNotEmpty()) {
                    categoryList = searchResult
                }

                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    list = categoryList,
                    query = query,
                    onQueryChange = { viewModel.updateQuery(it) }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Category>,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        CustomSearchBar(
            query = query,
            onValueChange = onQueryChange
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(
                items = list,
                key = { it.id }
            ) {
                CategoryListItem(
                    title = it.name,
                    emoji = it.emoji
                )

                HorizontalDivider()
            }
        }
    }
}