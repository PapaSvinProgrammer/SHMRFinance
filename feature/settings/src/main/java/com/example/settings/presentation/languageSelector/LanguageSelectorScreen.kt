package com.example.settings.presentation.languageSelector

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.settings.presentation.widget.content.LanguageList
import com.example.settings.presentation.widget.model.languagesList
import com.example.shmrfinance.ui.R
import com.example.utils.manager.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageSelectorScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val defaultLanguage = remember { LanguageManager.getDefaultLocale(context) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.select_language)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LanguageList(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            list = languagesList,
            currentSlug = defaultLanguage,
            onClick = {
                LanguageManager.setDefaultLocale(context, it.slug)
            }
        )
    }
}