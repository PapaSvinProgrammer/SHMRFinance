package com.example.bankaccountscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.bankaccountscreen.GetByIdBankAccount
import com.example.data.external.PreferencesRepository
import com.example.model.BankAccount
import com.example.ui.uiState.BankAccountUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankAccountViewModel @Inject constructor(
    private val getAllBankAccount: GetAllBankAccount,
    private val getByIdBankAccount: GetByIdBankAccount,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private var jobGetBankAccounts: Job? = null
    private var jobUpdateBankAccountId: Job? = null
    private var jobDefaultBankAccountId: Job? = null

    private var accountFlow = preferencesRepository.getCurrentAccountId()

    private val _currentBankAccount = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val currentBankAccount: StateFlow<BankAccountUIState> = _currentBankAccount

    private fun updateAccountId(id: Int) {
        jobUpdateBankAccountId?.cancel()

        jobUpdateBankAccountId = viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentAccountId(id)
        }
    }

    fun getBankAccount() {
        jobGetBankAccounts?.cancel()

        jobGetBankAccounts = viewModelScope.launch(Dispatchers.IO) {
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
    }

    private fun setDefaultBankAccountId() {
        jobDefaultBankAccountId?.cancel()

        jobDefaultBankAccountId = viewModelScope.launch(Dispatchers.IO) {
            var accounts: List<BankAccount> = listOf()

            getAllBankAccount.execute().onSuccess {
                accounts = it
            }.onFailure {
                _currentBankAccount.value = BankAccountUIState.Error(it)
            }

            if (accounts.isNotEmpty()) {
                val temp = accounts.first()
                updateAccountId(temp.id)
            }
        }
    }
}