package com.example.expenses.presentation

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.expenses.presentation.widget.UiState
import com.example.model.Transaction
import com.example.transaction.GetTransactionByType
import com.example.transaction.SaveTransaction
import com.example.transaction.model.GetTransactionParams
import com.example.ui.uiState.TransactionUIState
import com.example.utils.NoSelectBankAccount
import com.example.utils.cancelAllJobs
import com.example.utils.format.FormatDate
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class ExpensesViewModel @Inject constructor(
    private val getTransactionByType: GetTransactionByType,
    private val saveTransaction: SaveTransaction,
    preferencesRepository: PreferencesRepository,
) : ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactions = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _uiState = MutableStateFlow(UiState())
    val transactionState = _transactions.asStateFlow()
    val uiState = _uiState.asStateFlow()

    fun getTransactions() = launchWithoutOld(GET_TRANSACTIONS) {
        val currentDate = FormatDate.getCurrentDate()

        accountId.collect { id ->
            if (id != null) {
                val params = GetTransactionParams(
                    isIncome = false,
                    accountId = id,
                    startDate = currentDate,
                    endDate = currentDate
                )

                val response = getTransactionByType.execute(params)

                response.onSuccess {
                    updateTransactionState(it)
                    saveTransaction.execute(it)
                }.onFailure {
                    _transactions.value = TransactionUIState.Error(it)
                }
            }
            else {
                _transactions.value = TransactionUIState.Error(NoSelectBankAccount())
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        _transactions.value = TransactionUIState.Success(data)
        _uiState.value = uiState.value.copy(
            totalAmount = data.sumOf { it.amount },
            currency = data.firstOrNull()?.account?.currency
        )
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        private const val GET_TRANSACTIONS = "get_transactions"
    }
}