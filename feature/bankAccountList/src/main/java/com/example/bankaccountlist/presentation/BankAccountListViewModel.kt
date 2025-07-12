package com.example.bankaccountlist.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.data.external.PreferencesRepository
import com.example.ui.uiState.BankAccountUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val UPDATE_ACCOUNT_JOB = "update_account_id"
private const val GET_ACCOUNTS_JOB = "get_bank_accounts"

class BankAccountListViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val getAllBankAccount: GetAllBankAccount
): ViewModel() {
    val accountId = preferencesRepository.getCurrentAccountId()

    private val _accountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val accountState = _accountState.asStateFlow()

    init {
        getBankAccounts()
    }

    fun updateAccountId(id: Int) = launchWithoutOld(UPDATE_ACCOUNT_JOB) {
        preferencesRepository.setCurrentAccountId(id)
    }

    private fun getBankAccounts() = launchWithoutOld(GET_ACCOUNTS_JOB) {
        getAllBankAccount.execute(Unit).onSuccess {
            _accountState.value = BankAccountUIState.Success(it)
        }.onFailure {
            _accountState.value = BankAccountUIState.Error(it)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }
}