package com.example.createbankaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.CreateBankAccount
import com.example.model.AccountRequest
import com.example.model.CurrencyType
import com.example.model.toSlug
import com.example.ui.uiState.BankAccountUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBankAccountViewModel @Inject constructor(
    private val createBankAccount: CreateBankAccount
): ViewModel() {
    private val _name = MutableStateFlow("")
    private val _balance = MutableStateFlow(0f)
    private val _currency = MutableStateFlow(CurrencyType.RUB)
    val name: StateFlow<String> = _name
    val balance: StateFlow<Float> = _balance
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

    fun updateBalance(value: Float) {
        _balance.value = value
    }

    fun updateCurrency(value: CurrencyType) {
        _currency.value = value
    }

    fun updateErrorName(state: Boolean) {
        _errorName.value = state
    }

    fun createBankAccount() {
        _isStartCreate.value = true

        val request = AccountRequest(
            name = name.value,
            balance = balance.toString(),
            currency = currency.value.toSlug()
        )

        viewModelScope.launch(Dispatchers.IO) {
            createBankAccount.execute(request)
                .onSuccess {
                    _accountState.value = BankAccountUIState.Success(listOf(it))
                }
                .onFailure {
                    _accountState.value = BankAccountUIState.Error(it)
                }
        }
    }
}