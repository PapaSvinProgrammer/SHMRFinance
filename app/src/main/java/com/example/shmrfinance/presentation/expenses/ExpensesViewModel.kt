package com.example.shmrfinance.presentation.expenses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PreferencesRepository
import com.example.model.Transaction
import com.example.shmrfinance.utils.FormatDate
import com.example.shmrfinance.ui.uiState.TransactionUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getTransaction: com.example.domain.useCase.GetTransaction,
    preferencesRepository: PreferencesRepository
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()
    var transactionState by mutableStateOf(TransactionUIState.Loading as TransactionUIState)
        private set
    var totalAmount by mutableIntStateOf(0)
        private set
    var currency by mutableStateOf<String?>(null)
        private set

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDate = FormatDate.getCurrentDate()

            accountId.collect { id ->
                if (id != null) {
                    val response = getTransaction.getTransactionsType(
                        isIncome = false,
                        accountId = id,
                        startDate = currentDate,
                        endDate = currentDate
                    )

                    response.onSuccess {
                        updateTransactionState(it)
                    }.onError {
                        transactionState = TransactionUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        transactionState = TransactionUIState.Success(data)
        totalAmount = data.sumOf { it.amount.toInt() }
        currency = data.firstOrNull()?.account?.currency
    }
}