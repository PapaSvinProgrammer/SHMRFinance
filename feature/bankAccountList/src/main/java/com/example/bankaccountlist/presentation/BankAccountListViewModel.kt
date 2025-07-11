package com.example.bankaccountlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccountscreen.GetAllBankAccount
import com.example.data.external.PreferencesRepository
import com.example.ui.uiState.BankAccountUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankAccountListViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val getAllBankAccount: GetAllBankAccount
): ViewModel() {
    private var jobUpdateAccountId: Job? = null
    private var jobGetBankAccounts: Job? = null

    val accountId = preferencesRepository.getCurrentAccountId()

    private val _accountState = MutableStateFlow(BankAccountUIState.Loading as BankAccountUIState)
    val accountState: StateFlow<BankAccountUIState> = _accountState

    init {
        getBankAccounts()
    }

    fun updateAccountId(id: Int) {
        jobUpdateAccountId?.cancel()

        jobUpdateAccountId = viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentAccountId(id)
        }
    }

    private fun getBankAccounts() {
        jobGetBankAccounts?.cancel()

        jobGetBankAccounts = viewModelScope.launch(Dispatchers.IO) {
            getAllBankAccount.execute().onSuccess {
                _accountState.value = BankAccountUIState.Success(it)
            }.onFailure {
                _accountState.value = BankAccountUIState.Error(it)
            }
        }
    }
}