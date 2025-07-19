package com.example.tranasctionanalysis.presentation

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.model.Transaction
import com.example.tranasctionanalysis.domain.PrepareChartData
import com.example.tranasctionanalysis.presentation.widget.state.UIState
import com.example.tranasctionanalysis.presentation.widget.state.VisibleState
import com.example.transaction.GetTransactionByType
import com.example.transaction.model.GetTransactionParams
import com.example.ui.uiState.TransactionUIState
import com.example.utils.NoSelectBankAccount
import com.example.utils.cancelAllJobs
import com.example.utils.format.FormatDate
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class TransactionAnalysisViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getTransactionByType: GetTransactionByType,
    private val prepareChartData: PrepareChartData,
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _uiState = MutableStateFlow(UIState())
    private val _visibleState = MutableStateFlow(VisibleState())
    private val _transactionState = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    val uiState = _uiState.asStateFlow()
    val visibleState = _visibleState.asStateFlow()
    val transactionState = _transactionState.asStateFlow()

    private val _chartData = MutableStateFlow<Map<Float, String>>(mapOf())
    val chartData = _chartData.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            startDate = FormatDate.getCurrentMonthDate(),
            endDate = FormatDate.getCurrentDate()
        )
    }

    fun updateStartDate(date: Long) {
        _uiState.value = _uiState.value.copy(
            startDate = FormatDate.convertMillisToString(date)
        )
    }

    fun updateEndDate(date: Long) {
        _uiState.value = _uiState.value.copy(
            endDate = FormatDate.convertMillisToString(date)
        )
    }

    fun updateStartDatePickerVisible(state: Boolean) {
        _visibleState.value = _visibleState.value.copy(
            startDatePicker = state
        )
    }

    fun updateEndDatePickerVisible(state: Boolean) {
        _visibleState.value = _visibleState.value.copy(
            endDatePicker = state
        )
    }

    fun updateChartSheetVisible(state: Boolean) {
        _visibleState.value = _visibleState.value.copy(
            chartBottomSheetVisible = state
        )
    }

    fun getTransactions(isIncome: Boolean) = launchWithoutOld(TRANSACTIONS_JOB) {
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
                    prepareChartData(it)
                }.onFailure {
                    _transactionState.value = TransactionUIState.Error(it)
                }
            }
            else {
                _transactionState.value = TransactionUIState.Error(NoSelectBankAccount())
            }
        }
    }

    private fun prepareChartData(list: List<Transaction>) = launchWithoutOld(PREPARE_DATA_JOB) {
        _chartData.value = prepareChartData.execute(list)
    }

    private fun updateTransactionState(data: List<Transaction>) {
        _transactionState.value = TransactionUIState.Success(data)
        _uiState.value = uiState.value.copy(
            total = data.sumOf { it.amount },
            currency = data.firstOrNull()?.account?.currency ?: ""
        )
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val TRANSACTIONS_JOB = "get_transactions"
        const val PREPARE_DATA_JOB = "prepare_data"
    }
}