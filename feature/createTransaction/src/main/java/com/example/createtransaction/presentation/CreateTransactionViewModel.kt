package com.example.createtransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.category.GetAllCategory
import com.example.createtransaction.domain.FilterCategories
import com.example.createtransaction.presentation.widget.TransactionResponseUIState
import com.example.data.external.PreferencesRepository
import com.example.model.Category
import com.example.model.TransactionRequest
import com.example.transaction.CreateTransaction
import com.example.ui.uiState.BankAccountUIState
import com.example.ui.uiState.CategoryUIState
import com.example.utils.FormatDate
import com.example.utils.FormatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class CreateTransactionViewModel @Inject constructor(
    private val getByIdBankAccount: GetByIdBankAccount,
    private val getAllCategory: GetAllCategory,
    private val filterCategories: FilterCategories,
    private val createTransaction: CreateTransaction,
    preferencesRepository: PreferencesRepository
) : ViewModel() {
    private var currentBankAccountId: Int? = null
    private var jobGetCurrentBankAccount: Job? = null
    private var jobGetAllCategories: Job? = null

    private val _currentBankAccountId = preferencesRepository.getCurrentAccountId()
    private val _currentBankAccount =
        MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    private val _categories = MutableStateFlow(CategoryUIState.Loading as CategoryUIState)
    val currentBankAccount: StateFlow<BankAccountUIState> = _currentBankAccount
    val categories: StateFlow<CategoryUIState> = _categories

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

    private val _createResult =
        MutableStateFlow(TransactionResponseUIState.Loading as TransactionResponseUIState)
    private val _resultDialogVisible = MutableStateFlow(false)
    val createResult: StateFlow<TransactionResponseUIState> = _createResult
    val resultDialogVisible: StateFlow<Boolean> = _resultDialogVisible

    init {
        getCurrentBankAccount()

        _date.value = FormatDate.getCurrentDate()
        _time.value = FormatTime.getCurrentTime()
    }

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

    fun getAllCategories(isIncome: Boolean) {
        jobGetAllCategories?.cancel()

        jobGetAllCategories = viewModelScope.launch(Dispatchers.IO) {
            getAllCategory.execute().onSuccess {
                val filteredList = filterCategories.execute(
                    list = it,
                    isIncome = isIncome
                )

                _categories.value = CategoryUIState.Success(filteredList)
            }.onFailure {
                _categories.value = CategoryUIState.Error(it)
            }
        }
    }

    fun createTransaction() {
        if (currentBankAccountId == null) return

        if (currentCategory.value == null) return

        _resultDialogVisible.value = true
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

        viewModelScope.launch(Dispatchers.IO) {
            createTransaction.execute(request).onSuccess {
                _createResult.value = TransactionResponseUIState.Success(it)
            }.onFailure {
                _createResult.value = TransactionResponseUIState.Error(it)
            }
        }
    }

    private fun getCurrentBankAccount() {
        jobGetCurrentBankAccount?.cancel()

        jobGetCurrentBankAccount = viewModelScope.launch(Dispatchers.IO) {
            _currentBankAccountId.collect { id ->
                currentBankAccountId = id

                if (id != null) {
                    getByIdBankAccount.execute(id).onSuccess {
                        _currentBankAccount.value = BankAccountUIState.Success(listOf(it))
                    }.onFailure {
                        _currentBankAccount.value = BankAccountUIState.Error(it)
                    }
                }
            }
        }
    }
}