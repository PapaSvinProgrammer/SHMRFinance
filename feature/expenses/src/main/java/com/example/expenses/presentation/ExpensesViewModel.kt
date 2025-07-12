package com.example.expenses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
import com.example.model.Transaction
import com.example.transaction.GetTransactionByType
import com.example.ui.uiState.TransactionUIState
import com.example.utils.format.FormatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    private val getTransactionByType: GetTransactionByType,
    preferencesRepository: PreferencesRepository
): ViewModel() {
    private var jobGetTransactions: Job? = null

    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _totalAmount = MutableStateFlow(BigDecimal(0))
    private val _currency = MutableStateFlow<String?>(null)

    val transactionState: StateFlow<TransactionUIState> = _transactionState
    val totalAmount: StateFlow<BigDecimal> = _totalAmount
    val currency: StateFlow<String?> = _currency

    fun getTransactions() {
        jobGetTransactions?.cancel()

        jobGetTransactions = viewModelScope.launch(Dispatchers.IO) {
            val currentDate = FormatDate.getCurrentDate()

            accountId.collect { id ->
                if (id != null) {
                    val response = getTransactionByType.execute(
                        isIncome = false,
                        accountId = id,
                        startDate = currentDate,
                        endDate = currentDate
                    )

                    response.onSuccess {
                        updateTransactionState(it)
                    }.onFailure {
                        _transactionState.value = TransactionUIState.Error(it)
                    }
                }

                cancel()
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        _transactionState.value = TransactionUIState.Success(data)
        _totalAmount.value = data.sumOf { it.amount }
        _currency.value = data.firstOrNull()?.account?.currency
    }
}