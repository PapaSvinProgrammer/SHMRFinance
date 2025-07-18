package com.example.bankaccountscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.data.external.remote.PreferencesRepository
import com.example.model.BankAccount
import com.example.ui.uiState.BankAccountUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class BankAccountViewModel @Inject constructor(
    private val getAllBankAccount: GetAllBankAccount,
    private val getByIdBankAccount: GetByIdBankAccount,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private var accountFlow = preferencesRepository.getCurrentAccountId()

    private val _currentBankAccount = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val currentBankAccount = _currentBankAccount.asStateFlow()

    private fun updateAccountId(id: Int) = launchWithoutOld(UPDATE_ACCOUNT_JOB) {
        preferencesRepository.setCurrentAccountId(id)
    }

    fun getBankAccount() = launchWithoutOld(GET_ACCOUNT_JOB) {
        accountFlow.collect { accountId ->
            if (accountId == null) {
                setDefaultBankAccountId()
            }
            else {
                getByIdBankAccount.execute(accountId).onSuccess {
                    _currentBankAccount.value = BankAccountUIState.Success(listOf(it))
                }.onFailure {
                    _currentBankAccount.value = BankAccountUIState.Error(it)
                }
            }
        }
    }

    private fun setDefaultBankAccountId() = launchWithoutOld(SET_DEFAULT_JOB) {
        var accounts: List<BankAccount> = listOf()

        getAllBankAccount.execute(Unit).onSuccess {
            accounts = it
        }.onFailure {
            _currentBankAccount.value = BankAccountUIState.Error(it)
        }

        if (accounts.isNotEmpty()) {
            val temp = accounts.first()
            updateAccountId(temp.id)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val UPDATE_ACCOUNT_JOB = "update_account_id"
        const val GET_ACCOUNT_JOB = "get_bank_account"
        const val SET_DEFAULT_JOB = "set_default"
    }
}