package com.example.shmrfinance.presentation.bankAccountList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrfinance.domain.repository.PreferencesRepository
import com.example.shmrfinance.domain.useCase.GetBankAccount
import com.example.shmrfinance.ui.uiState.BankAccountUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BankAccountListViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val getBankAccount: GetBankAccount
): ViewModel() {
    val accountId = preferencesRepository.getCurrentAccountId()
    var accountState by mutableStateOf(BankAccountUIState.Loading as BankAccountUIState)
        private set

    init {
        getBankAccounts()
    }

    fun updateAccountId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentAccountId(id)
        }
    }

    private fun getBankAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            getBankAccount.getAll().onSuccess {
                accountState = BankAccountUIState.Success(it)
            }.onError {
                accountState = BankAccountUIState.Error(it)
            }
        }
    }
}