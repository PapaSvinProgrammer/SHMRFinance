package com.example.createbankaccount.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.CreateBankAccount
import com.example.model.AccountRequest
import com.example.model.CurrencyType
import com.example.model.toSlug
import com.example.ui.uiState.BankAccountUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal
import javax.inject.Inject

private const val CREATE_ACCOUNT_JOB = "create_bank_account"

class CreateBankAccountViewModel @Inject constructor(
    private val createBankAccount: CreateBankAccount
) : ViewModel() {
    private val _name = MutableStateFlow("")
    private val _balance = MutableStateFlow(BigDecimal(0))
    private val _currency = MutableStateFlow(CurrencyType.RUB)
    val name: StateFlow<String> = _name
    val balance: StateFlow<BigDecimal> = _balance
    val currency: StateFlow<CurrencyType> = _currency

    private val _errorName = MutableStateFlow(false)
    val errorName: StateFlow<Boolean> = _errorName

    private val _isStartCreate = MutableStateFlow(false)
    private val _accountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val isStartCreate: StateFlow<Boolean> = _isStartCreate
    val accountState: StateFlow<BankAccountUIState> = _accountState

    private val _visibleCurrencySheet = MutableStateFlow(false)
    val visibleCurrencySheet: StateFlow<Boolean> = _visibleCurrencySheet


    fun updateVisibleCurrencySheet(state: Boolean) {
        _visibleCurrencySheet.value = state
    }

    fun updateStartCreate(state: Boolean) {
        _isStartCreate.value = state
    }

    fun updateName(text: String) {
        _name.value = text
    }

    fun updateBalance(value: BigDecimal) {
        _balance.value = value
    }

    fun updateCurrency(value: CurrencyType) {
        _currency.value = value
    }

    fun updateErrorName(state: Boolean) {
        _errorName.value = state
    }

    fun createBankAccount() = launchWithoutOld(CREATE_ACCOUNT_JOB) {
        _isStartCreate.value = true

        val request = AccountRequest(
            name = name.value,
            balance = balance.toString(),
            currency = currency.value.toSlug()
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
}