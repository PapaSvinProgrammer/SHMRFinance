package com.example.shmrfinance.presentation.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PreferencesRepository
import com.example.model.Transaction
import com.example.shmrfinance.utils.FormatDate
import com.example.shmrfinance.ui.uiState.TransactionUIState
import com.example.transaction.GetTransactionByType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val getTransactionByType: GetTransactionByType,
    preferencesRepository: PreferencesRepository
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _totalAmount = MutableStateFlow(0)
    private val _currency = MutableStateFlow<String?>(null)

    var transactionState: StateFlow<TransactionUIState> = _transactionState
    var totalAmount: StateFlow<Int> = _totalAmount
    var currency: StateFlow<String?> = _currency

    fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDate = FormatDate.getCurrentDate()

            accountId.collect { id ->
                if (id != null) {
                    getTransactionByType.execute(
                        isIncome = true,
                        accountId = id,
                        startDate = currentDate,
                        endDate = currentDate
                    ).onSuccess {
                        updateTransactionState(it)
                    }.onFailure {
                        _transactionState.value = TransactionUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        _transactionState.value = TransactionUIState.Success(data)
        _totalAmount.value = data.sumOf { it.amount.toInt() }
        _currency.value = data.firstOrNull()?.account?.currency
    }
}