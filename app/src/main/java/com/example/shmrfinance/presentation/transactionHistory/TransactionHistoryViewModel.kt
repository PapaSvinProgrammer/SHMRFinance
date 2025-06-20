package com.example.shmrfinance.presentation.transactionHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PreferencesRepository
import com.example.model.Transaction
import com.example.shmrfinance.ui.uiState.TransactionUIState
import com.example.shmrfinance.utils.FormatDate
import com.example.transaction.GetTransactionByType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getTransactionByType: GetTransactionByType
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _totalAmount = MutableStateFlow(0)
    private val _currency = MutableStateFlow("")
    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")

    var transactionState: StateFlow<TransactionUIState> = _transactionState
    var totalAmount: StateFlow<Int> = _totalAmount
    var currency: StateFlow<String> = _currency
    var startDate: StateFlow<String> = _startDate
    var endDate: StateFlow<String> = _endDate

    init {
        _startDate.value = FormatDate.getCurrentMonthDate()
        _endDate.value = FormatDate.getCurrentDate()
    }

    fun getTransaction(isIncome: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            accountId.collect { id ->
                if (id != null) {
                    getTransactionByType.execute(
                        isIncome = isIncome,
                        accountId = id,
                        startDate = startDate.value,
                        endDate = endDate.value
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
        _totalAmount.value = data.sumOf { it.amount.toInt() }
        _transactionState.value = TransactionUIState.Success(data)
        _currency.value = data.firstOrNull()?.account?.currency ?: ""
    }
}