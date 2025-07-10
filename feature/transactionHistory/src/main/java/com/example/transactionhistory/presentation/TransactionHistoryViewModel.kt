package com.example.transactionhistory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
import com.example.model.Transaction
import com.example.transaction.GetTransactionByType
import com.example.ui.uiState.TransactionUIState
import com.example.utils.FormatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class TransactionHistoryViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getTransactionByType: GetTransactionByType
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _totalAmount = MutableStateFlow(BigDecimal(0))
    private val _currency = MutableStateFlow("")
    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")
    private val _visibleStartDatePicker = MutableStateFlow(false)
    private val _visibleEndDatePicker = MutableStateFlow(false)

    val transactionState: StateFlow<TransactionUIState> = _transactionState
    val totalAmount: StateFlow<BigDecimal> = _totalAmount
    val currency: StateFlow<String> = _currency
    val startDate: StateFlow<String> = _startDate
    val endDate: StateFlow<String> = _endDate
    val visibleStartDatePicker: StateFlow<Boolean> = _visibleStartDatePicker
    val visibleEndDatePicker: StateFlow<Boolean> = _visibleEndDatePicker

    init {
        _startDate.value = FormatDate.getCurrentMonthDate()
        _endDate.value = FormatDate.getCurrentDate()
    }

    fun updateStartDate(value: Long) {
        _startDate.value = FormatDate.convertMillisToString(value)
    }

    fun updateEndDate(value: Long) {
        _endDate.value = FormatDate.convertMillisToString(value)
    }

    fun updateVisibleEndDatePicker(state: Boolean) {
        _visibleEndDatePicker.value = state
    }

    fun updateVisibleStartDatePicker(state: Boolean) {
        _visibleStartDatePicker.value = state
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
        _totalAmount.value = data.sumOf { it.amount }
        _transactionState.value = TransactionUIState.Success(data)
        _currency.value = data.firstOrNull()?.account?.currency ?: ""
    }
}