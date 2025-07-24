package com.example.createtransaction.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.createtransaction.domain.FilterCategories
import com.example.createtransaction.presentation.widget.TransactionResponseUIState
import com.example.createtransaction.presentation.widget.UiState
import com.example.createtransaction.presentation.widget.VisibleState
import com.example.data.external.remote.PreferencesRepository
import com.example.model.Category
import com.example.model.TransactionRequest
import com.example.transaction.CreateTransaction
import com.example.ui.uiState.BankAccountUIState
import com.example.utils.NoSelectBankAccount
import com.example.utils.manager.cancelAllJobs
import com.example.utils.format.FormatDate
import com.example.utils.format.FormatTime
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject

internal class CreateTransactionViewModel @Inject constructor(
    private val getByIdBankAccount: GetByIdBankAccount,
    private val getAllCategory: GetAllCategory,
    private val filterCategories: FilterCategories,
    private val createTransaction: CreateTransaction,
    preferencesRepository: PreferencesRepository
) : ViewModel() {
    private var currentBankAccountId: Int? = null
    private val _bankAccountId = preferencesRepository.getCurrentAccountId()

    private val _uiState = MutableStateFlow(UiState())
    private val _visibleState = MutableStateFlow(VisibleState())
    val uiState = _uiState.asStateFlow()
    val visibleState = _visibleState.asStateFlow()

    private val _bankAccount = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val bankAccount = _bankAccount.asStateFlow()

    private val _createResult =
        MutableStateFlow(TransactionResponseUIState.Loading as TransactionResponseUIState)
    val createResult: StateFlow<TransactionResponseUIState> = _createResult

    init {
        getCurrentBankAccount()
        _uiState.value = uiState.value.copy(
            date = FormatDate.getCurrentDate(),
            time = FormatTime.getCurrentTime()
        )
    }

    fun updateComment(value: String) {
        _uiState.value = uiState.value.copy(
            comment = value
        )
    }

    fun updateDate(value: Long) {
        _uiState.value = uiState.value.copy(
            date = FormatDate.convertMillisToString(value)
        )
    }

    fun updateTime(value: String) {
        _uiState.value = uiState.value.copy(
            time = FormatTime.formatTimeToHHmm(value)
        )
    }

    fun updateCategoriesSheetVisible(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            categorySheet = state
        )
    }

    fun updateDatePickerVisible(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            datePicker = state
        )
    }

    fun updateTimePickerVisible(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            timePicker = state
        )
    }

    fun updateBalance(value: BigDecimal) {
        _uiState.value = uiState.value.copy(
            balance = value
        )
    }

    fun updateCurrentCategory(category: Category) {
        _uiState.value = uiState.value.copy(
            currentCategory = category
        )
    }

    fun updateResultDialogVisible(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = state
        )
    }

    fun getAllCategories(isIncome: Boolean) = launchWithoutOld(GET_CATEGORIES_JOB) {
        getAllCategory.execute(Unit).onSuccess {
            val filteredList = filterCategories.execute(
                list = it,
                isIncome = isIncome
            )

            _uiState.value = uiState.value.copy(
                categories = filteredList
            )
        }
    }

    fun createTransaction() = launchWithoutOld(CREATE_TRANSACTION_JOB) {
        if (currentBankAccountId == null || uiState.value.currentCategory == null) {
            return@launchWithoutOld
        }

        _visibleState.value = visibleState.value.copy(
            resultDialog = true
        )

        val request = TransactionRequest(
            accountId = currentBankAccountId ?: -1,
            categoryId = uiState.value.currentCategory?.id ?: -1,
            amount = uiState.value.balance.toString(),
            transactionDate = FormatDate.createRequestDate(
                dateStr = uiState.value.date,
                timeStr = uiState.value.time
            ),
            comment = uiState.value.comment
        )

        createTransaction.execute(request).onSuccess {
            _createResult.value = TransactionResponseUIState.Success(it)
        }.onFailure {
            _createResult.value = TransactionResponseUIState.Error(it)
        }
    }

    private fun getCurrentBankAccount() = launchWithoutOld(GET_CURRENT_ACCOUNT_JOB) {
        _bankAccountId.collect { id ->
            currentBankAccountId = id

            if (id != null) {
                getByIdBankAccount.execute(id).onSuccess {
                    _bankAccount.value = BankAccountUIState.Success(listOf(it))
                }.onFailure {
                    _bankAccount.value = BankAccountUIState.Error(it)
                }
            }
            else {
                _bankAccount.value = BankAccountUIState.Error(NoSelectBankAccount())
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        private const val GET_CATEGORIES_JOB = "get_all_categories"
        private const val CREATE_TRANSACTION_JOB = "create_transaction"
        private const val GET_CURRENT_ACCOUNT_JOB = "get_current_bank_account"
    }
}