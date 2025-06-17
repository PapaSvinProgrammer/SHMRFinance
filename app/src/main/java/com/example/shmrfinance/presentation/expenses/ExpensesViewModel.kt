package com.example.shmrfinance.presentation.expenses

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrfinance.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {
    override fun onCleared() {
        Log.d("RRRR", "CLEAR")
        super.onCleared()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = transactionRepository.getByPeriod(
                accountId = 57,
                startDate = "123",
                endDate = "123"
            )

            res.onSuccess {
                Log.d("RRRR", "SUCCESS $it")
            }.onError {
                Log.d("RRRR", "ERROR $it")
            }
        }
    }
}