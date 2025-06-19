package com.example.shmrfinance.presentation.transactionHistory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.ClientError
import com.example.data.repository.PreferencesRepository
import com.example.domain.useCase.GetTransaction
import com.example.model.Transaction
import com.example.shmrfinance.ui.uiState.TransactionUIState
import com.example.shmrfinance.utils.FormatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getTransaction: GetTransaction
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()
    var transactionState by mutableStateOf(TransactionUIState.Loading as TransactionUIState)
        private set
    var totalAmount by mutableIntStateOf(0)
        private set
    var currency by mutableStateOf("")
        private set
    var startDate by mutableStateOf("")
        private set
    var endDate by mutableStateOf("")
        private set

    init {
        startDate = FormatDate.getCurrentMonthDate()
        endDate = FormatDate.getCurrentDate()
    }

    fun getTransaction(isIncome: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            accountId.collect { id ->
                if (id == null) {
                    transactionState = TransactionUIState.Error(ClientError.ACCOUNT_ID)
                }
                else {
                    getTransaction.getTransactionsType(
                        isIncome = isIncome,
                        accountId = id,
                        startDate = startDate,
                        endDate = endDate
                    ).onSuccess {
                        updateTransactionState(it)
                    }.onError {
                        transactionState = TransactionUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        totalAmount = data.sumOf { it.amount.toInt() }
        transactionState = TransactionUIState.Success(data)
        currency = data.firstOrNull()?.account?.currency ?: ""
    }
}