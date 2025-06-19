package com.example.shmrfinance.presentation.bankAccount

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PreferencesRepository
import com.example.domain.useCase.GetBankAccount
import com.example.model.BankAccount
import com.example.shmrfinance.ui.uiState.BankAccountUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BankAccountViewModel @Inject constructor(
    private val getBankAccount: GetBankAccount,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private var accountFlow = preferencesRepository.getCurrentAccountId()
    var currentBankAccount by mutableStateOf(BankAccountUIState.Loading as BankAccountUIState)
        private set

    init {
        getBankAccount()
    }

    private fun updateAccountId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentAccountId(id)
        }
    }

    private fun getBankAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            accountFlow.collect { accountId ->
                if (accountId == null) {
                    setDefaultBankAccountId()
                }
                else {
                    getBankAccount.getById(accountId).onSuccess {
                        currentBankAccount = BankAccountUIState.Success(listOf(it))
                    }.onError {
                        currentBankAccount = BankAccountUIState.Error(it)
                    }
                }
            }
        }
    }

    private fun setDefaultBankAccountId() {
        viewModelScope.launch(Dispatchers.IO) {
            var accounts: List<BankAccount> = listOf()

            getBankAccount.getAll().onSuccess {
                accounts = it
            }.onError {
                currentBankAccount = BankAccountUIState.Error(it)
            }

            if (accounts.isNotEmpty()) {
                val temp = accounts.first()
                updateAccountId(temp.id)
            }
        }
    }
}