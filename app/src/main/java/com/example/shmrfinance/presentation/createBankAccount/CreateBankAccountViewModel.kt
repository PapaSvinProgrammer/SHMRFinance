package com.example.shmrfinance.presentation.createBankAccount

import android.graphics.Path.Op
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.reRequest
import com.example.network.model.BankAccount
import com.example.shmrfinance.domain.model.CurrencyType
import com.example.shmrfinance.domain.repository.BankAccountRepository
import com.example.shmrfinance.ui.uiState.BankAccountUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class CreateBankAccountViewModel @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
): ViewModel() {
    var name by mutableStateOf("")
        private set
    var balance by mutableFloatStateOf(0f)
        private set
    var currency by mutableStateOf(CurrencyType.RUB)
        private set

    var errorName by mutableStateOf(false)
        private set

    var isStartCreate by mutableStateOf(false)
        private set
    var accountState by mutableStateOf(BankAccountUIState.Loading as BankAccountUIState)
        private set

    var visibleCurrencySheet by mutableStateOf(false)
        private set

    fun updateVisibleCurrencySheet(state: Boolean) {
        visibleCurrencySheet = state
    }

    fun updateStartCreate(state: Boolean) {
        isStartCreate = state
    }

    fun updateName(text: String) {
        name = text
    }

    fun updateBalance(value: Float) {
        balance = value
    }

    fun updateCurrency(value: CurrencyType) {
        currency = value
    }

    fun updateErrorName(state: Boolean) {
        errorName = state
    }

    fun createBankAccount() {
        isStartCreate = true

        viewModelScope.launch(Dispatchers.IO) {
            var moreRequest = false

            testTask().onError {
                if (it == NetworkError.SERVER_ERROR) {
                    moreRequest = true
                }
            }

            if (moreRequest) {
                Log.d("RRRR", "START RE_REQUEST")
                reRequest {
                    testTask()
                }
            }

            accountState = BankAccountUIState.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun testTask(): Operation<List<BankAccount>, NetworkError> {
        delay(100)
        return Operation.Error(NetworkError.SERVER_ERROR)
    }
}