package com.example.updatetransaction.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.data.external.remote.PreferencesRepository
import com.example.model.Category
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.transaction.DeleteTransaction
import com.example.transaction.GetByIdTransaction
import com.example.transaction.UpdateTransaction
import com.example.transaction.model.UpdateTransactionParams
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.uiState.TransactionUIState
import com.example.updatetransaction.presentation.widget.UiState
import com.example.updatetransaction.presentation.widget.VisibleState
import com.example.utils.NoSelectBankAccount
import com.example.utils.SuccessDeleteTransactionException
import com.example.utils.cancelAllJobs
import com.example.utils.format.FormatDate
import com.example.utils.format.FormatTime
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject

internal class UpdateTransactionViewModel @Inject constructor(
    private val updateTransaction: UpdateTransaction,
    private val getByIdTransaction: GetByIdTransaction,
    private val deleteTransaction: DeleteTransaction,
    private val getAllCategory: GetAllCategory,
    private val getByIdBankAccount: GetByIdBankAccount,
    preferencesRepository: PreferencesRepository
) : ViewModel() {
    private var currentBankAccountId: Int? = null
    private val _bankAccountId = preferencesRepository.getCurrentAccountId()

    private val _uiState = MutableStateFlow(UiState())
    private val _visibleState = MutableStateFlow(VisibleState())
    val uiState = _uiState.asStateFlow()
    val visibleState = _visibleState.asStateFlow()

    private val _bankAccount = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    private val _updateResult = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    val bankAccount = _bankAccount.asStateFlow()
    val updateResult = _updateResult.asStateFlow()

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

    fun getTransactionById(id: Int) = launchWithoutOld(GET_TRANSACTION_JOB) {
        getByIdTransaction.execute(id).onSuccess {
            updateTransactionData(it)
            _uiState.value = uiState.value.copy(
                transaction = listOf(it)
            )
        }
    }

    fun updateTransaction(transactionId: Int) = launchWithoutOld(UPDATE_TRANSACTION_JOB) {
        if (uiState.value.balance == BigDecimal(0) || uiState.value.currentCategory == null) {
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

        val params = UpdateTransactionParams(
            id = transactionId,
            request = request
        )

        updateTransaction.execute(params).onSuccess { transaction ->
            _updateResult.value = TransactionUIState.Success(listOf(transaction))
        }.onFailure { error ->
            _updateResult.value = TransactionUIState.Error(error)
        }
    }

    fun deleteTransaction(transactionId: Int) = launchWithoutOld(DELETE_TRANSACTION_JOB) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = true
        )

        deleteTransaction.execute(transactionId).onFailure {
            when (it) {
                is SuccessDeleteTransactionException -> {
                    _updateResult.value = TransactionUIState.Success(listOf())
                }

                else -> {
                    _updateResult.value = TransactionUIState.Error(it)
                }
            }
        }
    }

    fun getAllCategories() = launchWithoutOld(GET_CATEGORIES_JOB) {
        getAllCategory.execute(Unit).onSuccess {
            _uiState.value = uiState.value.copy(
                categories = it
            )
        }
    }

    fun getBankAccount() = launchWithoutOld(GET_ACCOUNT_JOB) {
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

    private fun updateTransactionData(transaction: Transaction) {
        _uiState.value = uiState.value.copy(
            currentCategory = transaction.category,
            balance = transaction.amount,
            date = FormatDate.convertDateFromISO(transaction.transactionDate),
            time = FormatTime.convertTimeFromISO(transaction.transactionDate),
            comment = transaction.comment
        )
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_CATEGORIES_JOB = "get_all_categories"
        const val UPDATE_TRANSACTION_JOB = "update_transaction"
        const val GET_TRANSACTION_JOB = "get_transaction_by_id"
        const val DELETE_TRANSACTION_JOB = "delete_transaction"
        const val GET_ACCOUNT_JOB = "get_bank_account"
    }
}