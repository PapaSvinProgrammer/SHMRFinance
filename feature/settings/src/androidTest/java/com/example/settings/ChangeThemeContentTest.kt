package com.example.settings

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.settings.presentation.settings.SettingsScreen
import com.example.settings.presentation.settings.SettingsViewModel
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

private class FakeSettingsViewModel : SettingsViewModel(mock()) {
    private val _darkTheme = MutableStateFlow(false)
    override val darkTheme: StateFlow<Boolean> get() = _darkTheme
    override fun setDarkTheme(state: Boolean) = launchWithoutOld {
        _darkTheme.value = state
    }
}

class ChangeThemeContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun switch_changes_state_on_settings_screen() {
        val viewModel = FakeSettingsViewModel()
        composeTestRule.setContent {
            SettingsScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }
        composeTestRule.onNodeWithTag("theme_switch").assertIsOff()
        composeTestRule.onNodeWithTag("theme_switch").performClick()
        composeTestRule.onNodeWithTag("theme_switch").assertIsOn()
    }
} 