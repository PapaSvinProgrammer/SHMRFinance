package com.example.shmrfinance.presentation.bankAccount

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrfinance.domain.repository.BankAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BankAccountViewModel @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            bankAccountRepository.getAll()
                .onSuccess {
                    Log.d("RRRR", it.toString())
                }
                .onError {
                    Log.d("RRRR", it.toString())
                }
        }
    }
}