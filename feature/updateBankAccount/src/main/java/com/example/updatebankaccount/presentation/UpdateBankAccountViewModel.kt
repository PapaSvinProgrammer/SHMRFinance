package com.example.updatebankaccount.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.DeleteBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.bankaccountscreen.UpdateBankAccount
import com.example.bankaccountscreen.model.UpdateBankParams
import com.example.utils.SuccessDeleteTransactionException
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.CurrencyType
import com.example.model.toCurrencyType
import com.example.model.toSlug
import com.example.ui.uiState.BankAccountUIState
import com.example.updatebankaccount.presentation.widget.UiState
import com.example.updatebankaccount.presentation.widget.VisibleState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val UPDATE_ACCOUNT_JOB = "update_bank_account"
private const val GET_BANK_ACCOUNT_JOB = "get_bank_account"
private const val DELETE_ACCOUNT_JOB = "delete_bank_account"

class UpdateBankAccountViewModel @Inject constructor(
    private val getBankAccount: GetByIdBankAccount,
    private val updateBankAccount: UpdateBankAccount,
    private val deleteBankAccount: DeleteBankAccount
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    private val _visibleState = MutableStateFlow(VisibleState())
    val uiState = _uiState.asStateFlow()
    val visibleState = _visibleState.asStateFlow()

    private val _accountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val accountState = _accountState.asStateFlow()

    private val _resultState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val resultState = _resultState.asStateFlow()

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

    fun updateCurrency(value: CurrencyType) {
        _uiState.value = uiState.value.copy(
            currency = value
        )
    }

    fun getBankAccount(id: Int) = launchWithoutOld(GET_BANK_ACCOUNT_JOB) {
        getBankAccount.execute(id).onSuccess {
            updateFields(it)
        }.onFailure {
            _accountState.value = BankAccountUIState.Error(it)
        }
    }

    fun updateBankAccount(id: Int) = launchWithoutOld(UPDATE_ACCOUNT_JOB) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = true
        )

        val request = AccountRequest(
            name = uiState.value.name,
            balance = uiState.value.balance.toString(),
            currency = uiState.value.currency.toSlug()
        )

        val params = UpdateBankParams(
            id = id,
            request = request
        )

        updateBankAccount.execute(params).onSuccess {
            _resultState.value = BankAccountUIState.Success(listOf(it))
        }.onFailure {
            _resultState.value = BankAccountUIState.Error(it)
        }
    }

    fun deleteBankAccount(id: Int) = launchWithoutOld(DELETE_ACCOUNT_JOB) {
        _visibleState.value = visibleState.value.copy(
            resultDialog = true
        )

        deleteBankAccount.execute(id).onFailure {
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

    private fun updateFields(account: BankAccount) {
        _uiState.value = uiState.value.copy(
            name = account.name,
            balance = account.balance,
            currency = account.currency.toCurrencyType()
        )

        _accountState.value = BankAccountUIState.Success(listOf(account))
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }
}