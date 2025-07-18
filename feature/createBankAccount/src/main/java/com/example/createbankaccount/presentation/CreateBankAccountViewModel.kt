package com.example.createbankaccount.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.CreateBankAccount
import com.example.createbankaccount.presentation.widget.UiState
import com.example.createbankaccount.presentation.widget.VisibleState
import com.example.model.AccountRequest
import com.example.model.CurrencyType
import com.example.model.toSlug
import com.example.ui.uiState.BankAccountUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject

internal class CreateBankAccountViewModel @Inject constructor(
    private val createBankAccount: CreateBankAccount
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    private val _visibleState = MutableStateFlow(VisibleState())
    val uiState = _uiState.asStateFlow()
    val visibleState = _visibleState.asStateFlow()

    private val _accountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val accountState = _accountState.asStateFlow()

    fun updateVisibleCurrencySheet(state: Boolean) {
       _visibleState.value = visibleState.value.copy(
           currencySheet = state
       )
    }

    fun updateVisibleResultDialog(state: Boolean) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = state
        )
    }

    fun updateName(text: String) {
        _uiState.value = uiState.value.copy(
            name = text
        )
    }

    fun updateBalance(value: BigDecimal) {
        _uiState.value = uiState.value.copy(
            balance = value
        )
    }

    fun updateCurrency(value: CurrencyType) {
        _uiState.value = uiState.value.copy(
            currency = value
        )
    }

    fun updateErrorName(state: Boolean) {
        _uiState.value = uiState.value.copy(
            errorName = state
        )
    }

    fun createBankAccount() = launchWithoutOld(CREATE_ACCOUNT_JOB) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = true
        )

        val request = AccountRequest(
            name = uiState.value.name,
            balance = uiState.value.balance.toString(),
            currency = uiState.value.currency.toSlug()
        )

        createBankAccount.execute(request)
            .onSuccess {
                _accountState.value = BankAccountUIState.Success(listOf(it))
            }
            .onFailure {
                _accountState.value = BankAccountUIState.Error(it)
            }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val CREATE_ACCOUNT_JOB = "create_bank_account"
    }
}