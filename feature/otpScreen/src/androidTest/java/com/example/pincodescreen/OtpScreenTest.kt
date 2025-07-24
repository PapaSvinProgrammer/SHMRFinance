package com.example.pincodescreen

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pincodescreen.presentation.widget.content.MainOtpContent
import com.example.pincodescreen.presentation.widget.state.OtpAction
import com.example.pincodescreen.presentation.widget.state.UiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@RunWith(AndroidJUnit4::class)
class OtpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun main_otp_content_pin_input() {
        val focusRequesters = List(4) { FocusRequester() }
        composeTestRule.setContent {

            var state by remember { mutableStateOf(UiState(code = listOf(null, null, null, null))) }

            MainOtpContent(
                state = state,
                focusRequesters = focusRequesters,
                onAction = { action ->
                    when (action) {
                        is OtpAction.OnEnterNumber -> {
                            val newCode = state.code.toMutableList()
                            newCode[action.index] = action.number
                            state = state.copy(code = newCode)
                        }

                        else -> {}
                    }
                }
            )
        }

        val pinNodes = composeTestRule.onAllNodesWithTag("OtpInputField")

        pinNodes[0].performTextInput("1")
        pinNodes[1].performTextInput("2")
        pinNodes[2].performTextInput("3")
        pinNodes[3].performTextInput("4")

        pinNodes[0].assertTextEquals("1")
        pinNodes[1].assertTextEquals("2")
        pinNodes[2].assertTextEquals("3")
        pinNodes[3].assertTextEquals("4")
    }
}
