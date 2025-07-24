package com.example.transactionhistory.presentation

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.model.Transaction
import com.example.transaction.GetTransactionByType
import com.example.transaction.SaveTransaction
import com.example.transaction.model.GetTransactionParams
import com.example.transactionhistory.presentation.widget.UiState
import com.example.transactionhistory.presentation.widget.VisibleState
import com.example.ui.uiState.TransactionUIState
import com.example.utils.NoSelectBankAccount
import com.example.utils.manager.cancelAllJobs
import com.example.utils.format.FormatDate
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class TransactionHistoryViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getTransactionByType: GetTransactionByType,
    private val saveTransaction: SaveTransaction
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _visibleState = MutableStateFlow(VisibleState())
    private val _uiState = MutableStateFlow(UiState())
    val visibleState = _visibleState.asStateFlow()
    val transactionState = _transactionState.asStateFlow()
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = uiState.value.copy(
            startDate = FormatDate.getCurrentMonthDate(),
            endDate = FormatDate.getCurrentDate()
        )
    }

    fun updateStartDate(value: Long) {
        _uiState.value = _uiState.value.copy(
            startDate = FormatDate.convertMillisToString(value)
        )
    }

    fun updateEndDate(value: Long) {
        _uiState.value = _uiState.value.copy(
            endDate = FormatDate.convertMillisToString(value)
        )
    }

    fun updateVisibleEndDatePicker(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            endDatePicker = state
        )
    }

    fun updateVisibleStartDatePicker(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            startDatePicker = state
        )
    }

    fun getTransaction(isIncome: Boolean) = launchWithoutOld(GET_TRANSACTION_JOB) {
        accountId.collect { id ->
            if (id != null) {
                val params = GetTransactionParams(
                    isIncome = isIncome,
                    accountId = id,
                    startDate = uiState.value.startDate,
                    endDate = uiState.value.endDate
                )

                getTransactionByType.execute(params).onSuccess {
                    updateTransactionState(it)
                    saveTransaction.execute(it)
                }.onFailure {
                    _transactionState.value = TransactionUIState.Error(it)
                }
            }
            else {
                _transactionState.value = TransactionUIState.Error(NoSelectBankAccount())
            }
        }
    }

    private fun updateTransactionState(data: List<Transaction>) {
        _transactionState.value = TransactionUIState.Success(data)
        _uiState.value = uiState.value.copy(
            totalAmount = data.sumOf { it.amount },
            currency = data.firstOrNull()?.account?.currency ?: ""
        )
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_TRANSACTION_JOB = "get_transactions"
    }
}