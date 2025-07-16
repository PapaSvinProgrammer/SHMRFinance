package com.example.updatetransaction.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ui.navigation.BankAccountListRoute
import com.example.shmrfinance.updateTransaction.R
import com.example.ui.dialog.ResultDialog
import com.example.ui.dialog.toResultType
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.uiState.TransactionUIState
import com.example.ui.widget.bottomSheet.CategoriesBottomSheet
import com.example.ui.widget.components.BasicLoadingScreen
import com.example.ui.widget.components.MainTransactionContent
import com.example.ui.widget.dialog.DefaultDatePickerDialog
import com.example.ui.widget.dialog.DefaultTimeInputDialog
import com.example.utils.format.FormatDate
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransactionScreen(
    navController: NavController,
    viewModel: UpdateTransactionViewModel,
    transactionId: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val visibleState by viewModel.visibleState.collectAsStateWithLifecycle()
    val updateResult by viewModel.updateResult.collectAsStateWithLifecycle()
    val bankAccount by viewModel.bankAccount.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var isBalanceError by remember { mutableStateOf(false) }
    var isCategoryError by remember { mutableStateOf(false) }

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getTransactionById(transactionId)
        viewModel.getAllCategories()
        viewModel.getBankAccount()
    }

    LaunchedEffect(isBalanceError) {
        if (isBalanceError) {
            snackBarHostState.showSnackbar(
                message = context.getString(R.string.empty_transaction_error)
            )
            isBalanceError = false
        }
    }

    LaunchedEffect(isCategoryError) {
        if (isCategoryError) {
            snackBarHostState.showSnackbar(
                message = context.getString(R.string.empty_category_error)
            )
            isCategoryError = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.edit))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (uiState.balance == BigDecimal(0)) {
                                isBalanceError = true
                            } else if (uiState.currentCategory == null) {
                                isCategoryError = true
                            } else {
                                viewModel.updateTransaction(transactionId)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->
        when (val state = bankAccount) {
            is BankAccountUIState.Error -> {}
            BankAccountUIState.Loading -> BasicLoadingScreen(Modifier.fillMaxSize())
            is BankAccountUIState.Success -> {
                Column {
                    val dateStr = if (uiState.date.isEmpty())
                        ""
                    else
                        FormatDate.getPrettyDate(uiState.date)

                    MainTransactionContent(
                        modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                        bankAccount = state.data.first(),
                        balance = uiState.balance,
                        category = uiState.currentCategory,
                        date = dateStr,
                        time = uiState.time,
                        comment = uiState.comment ?: "",
                        onBankAccountClick = {
                            navController.navigate(BankAccountListRoute) {
                                launchSingleTop = true
                            }
                        },
                        onCategoryClick = {
                            viewModel.updateCategoriesSheetVisible(true)
                        },
                        onDateClick = {
                            viewModel.updateDatePickerVisible(true)
                        },
                        onTimeClick = {
                            viewModel.updateTimePickerVisible(true)
                        },
                        onChangeBalance = {
                            viewModel.updateBalance(it)
                        },
                        onCommentChange = {
                            viewModel.updateComment(it)
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        ),
                        onClick = {
                            viewModel.deleteTransaction(transactionId)
                        }
                    ) {
                        Text(text = stringResource(R.string.delete_transaction))
                    }
                }
            }
        }
    }

    if (visibleState.datePicker) {
        DefaultDatePickerDialog(
            selectedDate = FormatDate.convertStringToMillis(uiState.date),
            onDateSelected = { viewModel.updateDate(it ?: 0) },
            onDismiss = { viewModel.updateDatePickerVisible(false) }
        )
    }

    if (visibleState.timePicker) {
        DefaultTimeInputDialog(
            onConfirmClick = {
                viewModel.updateTime(it)
            },
            onDismissClick = { viewModel.updateTimePickerVisible(false) }
        )
    }

    if (visibleState.categorySheet) {
        CategoriesBottomSheet(
            currentCategory = uiState.currentCategory,
            categories = uiState.categories,
            onConfirmClick = { category ->
                viewModel.updateCurrentCategory(category)
            },
            onDismissClick = {
                viewModel.updateCategoriesSheetVisible(false)
            }
        )
    }

    if (visibleState.resultDialog) {
        ResultDialog(
            type = updateResult.toResultType(),
            error = (updateResult as? TransactionUIState.Error)?.error,
            onDismissRequest = {
                viewModel.updateResultDialogVisible(false)
                navController.popBackStack()
            }
        )
    }
}