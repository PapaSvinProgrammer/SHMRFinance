package com.example.updatebankaccount.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.DeleteBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.bankaccountscreen.UpdateBankAccount
import com.example.common.SuccessDeleteTransactionException
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.CurrencyType
import com.example.model.toCurrencyType
import com.example.model.toSlug
import com.example.ui.uiState.BankAccountUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class UpdateBankAccountViewModel @Inject constructor(
    private val getBankAccount: GetByIdBankAccount,
    private val updateBankAccount: UpdateBankAccount,
    private val deleteBankAccount: DeleteBankAccount
): ViewModel() {
    private val _bankAccountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    private val _name = MutableStateFlow("")
    private val _currency = MutableStateFlow(CurrencyType.RUB)
    private val _balance = MutableStateFlow(BigDecimal(0))
    val bankAccountState: StateFlow<BankAccountUIState> = _bankAccountState
    val name: StateFlow<String> = _name
    val currency: StateFlow<CurrencyType> = _currency
    val balance: StateFlow<BigDecimal> = _balance

    private val _resultState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    private val _visibleResultDialog = MutableStateFlow(false)
    val resultState: StateFlow<BankAccountUIState> = _resultState
    val visibleResultDialog: StateFlow<Boolean> = _visibleResultDialog

    private val _visibleCurrencySheet = MutableStateFlow(false)
    val visibleCurrencySheet: StateFlow<Boolean> = _visibleCurrencySheet

    fun getBankAccount(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getBankAccount.execute(id).onSuccess {
                updateFields(it)
            }.onFailure {
                _bankAccountState.value = BankAccountUIState.Error(it)
            }
        }
    }

    fun updateVisibleCurrencySheet(state: Boolean) {
        _visibleCurrencySheet.value = state
    }

    fun updateName(value: String) {
        _name.value = value
    }

    fun updateCurrency(value: CurrencyType) {
        _currency.value = value
    }

    fun updateBankAccount(id: Int) {
        _visibleResultDialog.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val request = AccountRequest(
                name = name.value,
                balance = balance.value.toString(),
                currency = currency.value.toSlug()
            )

            updateBankAccount.execute(
                id = id,
                accountRequest = request
            ).onSuccess {
                _resultState.value = BankAccountUIState.Success(listOf(it))
            }.onFailure {
                _resultState.value = BankAccountUIState.Error(it)
            }
        }
    }

    fun deleteBankAccount(id: Int) {
        _visibleResultDialog.value = true

        viewModelScope.launch(Dispatchers.IO) {
            deleteBankAccount.execute(id).onFailure {
                Log.d("RRRR", it.toString())
                when (it) {
                    is SuccessDeleteTransactionException -> {
                        _resultState.value = BankAccountUIState.Success(listOf())
                    }
                    else -> {
                        _resultState.value = BankAccountUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun updateFields(account: BankAccount) {
        _name.value = account.name
        _balance.value = account.balance
        _currency.value = account.currency.toCurrencyType()

        _bankAccountState.value = BankAccountUIState.Success(listOf(account))
    }
}