package com.example.shmrfinance.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.shmrfinance.R
import com.example.shmrfinance.articleList
import com.example.shmrfinance.ui.widget.components.CustomSearchBar
import com.example.shmrfinance.ui.widget.listItems.CategoryListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen() {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.my_articles))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            CustomSearchBar(query) { query = it }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(articleList) {
                    CategoryListItem(
                        title = it.name,
                        emoji = it.emoji
                    )

                    HorizontalDivider()
                }
            }
        }
    }
}