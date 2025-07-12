package com.example.updatetransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.common.SuccessDeleteTransactionException
import com.example.data.external.PreferencesRepository
import com.example.model.Category
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.transaction.DeleteTransaction
import com.example.transaction.GetByIdTransaction
import com.example.transaction.UpdateTransaction
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.uiState.CategoryUIState
import com.example.ui.uiState.TransactionUIState
import com.example.utils.format.FormatDate
import com.example.utils.format.FormatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class UpdateTransactionViewModel @Inject constructor(
    private val updateTransaction: UpdateTransaction,
    private val getByIdTransaction: GetByIdTransaction,
    private val deleteTransaction: DeleteTransaction,
    private val getAllCategory: GetAllCategory,
    private val getByIdBankAccount: GetByIdBankAccount,
    preferencesRepository: PreferencesRepository
) : ViewModel() {
    private var currentBankAccountId: Int? = null

    private var jobGetAllCategory: Job? = null
    private var jobUpdateTransaction: Job? = null
    private var jobGetTransactionById: Job? = null
    private var jobDeleteTransaction: Job? = null
    private var jobGetBankAccount: Job? = null

    private val _bankAccountId = preferencesRepository.getCurrentAccountId()
    private val _transaction = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _categories = MutableStateFlow(CategoryUIState.Loading as CategoryUIState)
    private val _bankAccount = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val categories: StateFlow<CategoryUIState> = _categories
    val bankAccount: StateFlow<BankAccountUIState> = _bankAccount

    private val _currentCategory = MutableStateFlow<Category?>(null)
    private val _date = MutableStateFlow("")
    private val _time = MutableStateFlow("")
    private val _balance = MutableStateFlow(BigDecimal(0))
    private val _comment = MutableStateFlow<String?>(null)
    val currentCategory: StateFlow<Category?> = _currentCategory
    val date: StateFlow<String> = _date
    val time: StateFlow<String> = _time
    val balance: StateFlow<BigDecimal> = _balance
    val comment: StateFlow<String?> = _comment

    private val _datePickerVisible = MutableStateFlow(false)
    private val _timePickerVisible = MutableStateFlow(false)
    private val _categorySheetVisible = MutableStateFlow(false)
    val datePickerVisible: StateFlow<Boolean> = _datePickerVisible
    val timePickerVisible: StateFlow<Boolean> = _timePickerVisible
    val categorySheetVisible: StateFlow<Boolean> = _categorySheetVisible

    private val _updateResult = MutableStateFlow(TransactionUIState.Loading as TransactionUIState)
    private val _resultDialogVisible = MutableStateFlow(false)
    val updateResult: StateFlow<TransactionUIState> = _updateResult
    val resultDialogVisible: StateFlow<Boolean> = _resultDialogVisible

    fun updateComment(value: String) {
        _comment.value = value
    }

    fun updateDate(value: Long) {
        _date.value = FormatDate.convertMillisToString(value)
    }

    fun updateTime(value: String) {
        _time.value = value
    }

    fun updateCategoriesSheetVisible(state: Boolean) {
        _categorySheetVisible.value = state
    }

    fun updateDatePickerVisible(state: Boolean) {
        _datePickerVisible.value = state
    }

    fun updateTimePickerVisible(state: Boolean) {
        _timePickerVisible.value = state
    }

    fun updateBalance(value: BigDecimal) {
        _balance.value = value
    }

    fun updateCurrentCategory(category: Category) {
        _currentCategory.value = category
    }

    fun updateResultDialogVisible(state: Boolean) {
        _resultDialogVisible.value = state
    }

    fun getTransactionById(id: Int) {
        jobGetTransactionById?.cancel()

        jobGetTransactionById = viewModelScope.launch(Dispatchers.IO) {
            getByIdTransaction.execute(id).onSuccess {
                updateTransactionData(it)
                _transaction.value = TransactionUIState.Success(listOf(it))
            }.onFailure {
                _transaction.value = TransactionUIState.Error(it)
            }
        }
    }

    fun updateTransaction(transactionId: Int) {
        if (balance.value == BigDecimal(0)) return

        if (currentCategory.value == null) return

        val request = TransactionRequest(
            accountId = currentBankAccountId ?: -1,
            categoryId = currentCategory.value?.id ?: -1,
            amount = balance.value.toString(),
            transactionDate = FormatDate.createRequestDate(
                dateStr = date.value,
                timeStr = time.value
            ),
            comment = comment.value
        )

        jobUpdateTransaction?.cancel()
        _resultDialogVisible.value = true

        jobUpdateTransaction = viewModelScope.launch(Dispatchers.IO) {
            updateTransaction.execute(
                id = transactionId,
                request = request
            ).onSuccess { transaction ->
                _updateResult.value = TransactionUIState.Success(listOf(transaction))
            }.onFailure { error ->
                _updateResult.value = TransactionUIState.Error(error)
            }
        }
    }

    fun deleteTransaction(transactionId: Int) {
        jobDeleteTransaction?.cancel()
        _resultDialogVisible.value = true

        jobDeleteTransaction = viewModelScope.launch(Dispatchers.IO) {
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
    }

    fun getAllCategories() {
        jobGetAllCategory?.cancel()

        jobGetAllCategory = viewModelScope.launch(Dispatchers.IO) {
            getAllCategory.execute().onSuccess {
                _categories.value = CategoryUIState.Success(it)
            }.onFailure {
                _categories.value = CategoryUIState.Error(it)
            }
        }
    }

    fun getBankAccount() {
        jobGetBankAccount?.cancel()

        jobGetBankAccount = viewModelScope.launch(Dispatchers.IO) {
            _bankAccountId.collect { id ->
                currentBankAccountId = id

                if (id != null) {
                    getByIdBankAccount.execute(id).onSuccess {
                        _bankAccount.value = BankAccountUIState.Success(listOf(it))
                    }.onFailure {
                        _bankAccount.value = BankAccountUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun updateTransactionData(transaction: Transaction) {
        _balance.value = transaction.amount
        _currentCategory.value = transaction.category
        _date.value = FormatDate.convertDateFromISO(transaction.transactionDate)
        _time.value = FormatTime.convertTimeFromISO(transaction.transactionDate)
        _comment.value = transaction.comment
    }
}