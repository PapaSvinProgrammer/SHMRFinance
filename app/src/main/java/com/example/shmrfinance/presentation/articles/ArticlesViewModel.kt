package com.example.shmrfinance.presentation.articles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PreferencesRepository
import com.example.domain.useCase.GetBankAccount
import com.example.model.BankAccount
import com.example.model.Category
import com.example.model.StatItem
import com.example.shmrfinance.ui.uiState.CategoryUIState
import com.example.shmrfinance.utils.toCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getBankAccount: GetBankAccount
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()
    var categoryState by mutableStateOf(CategoryUIState.Loading as CategoryUIState)
        private set

    var query by mutableStateOf("")
        private set
    var searchResult by mutableStateOf<List<Category>>(listOf())
        private set

    init {
        getCategories()
    }

    fun updateQuery(value: String) {
        query = value
    }

    fun searchCategory() {
        viewModelScope.launch(Dispatchers.Default) {
           (categoryState as? CategoryUIState.Success)?.data?.let { data ->
               searchResult = data.filter { it.name.lowercase().contains(query.lowercase()) }
           }
        }
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            accountId.collect { id ->
                if (id == null) {
                    categoryState = CategoryUIState.Success(listOf())
                }
                else {
                    getBankAccount.getById(id).onSuccess {
                        updateCategoryList(it)
                    }.onError {
                        categoryState = CategoryUIState.Error(it)
                    }
                }

                cancel()
            }
        }
    }

    private fun updateCategoryList(account: BankAccount) {
        val res = mutableSetOf<StatItem>()

        res.addAll(account.expensesStats)
        res.addAll(account.incomeStats)

        categoryState = CategoryUIState.Success(res.map { it.toCategory() })
    }
}