package com.example.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.category.GetAllCategory
import com.example.data.external.PreferencesRepository
import com.example.model.Category
import com.example.ui.uiState.CategoryUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getAllCategory: GetAllCategory
): ViewModel() {
    private val accountId = preferencesRepository.getCurrentAccountId()

    private val _categoryState = MutableStateFlow(CategoryUIState.Loading as CategoryUIState)
    private val _searchResult = MutableStateFlow<List<Category>>(listOf())
    private val _query = MutableStateFlow("")

    val categoryState: StateFlow<CategoryUIState> = _categoryState
    val searchResult: StateFlow<List<Category>> = _searchResult
    val query: StateFlow<String> = _query

    init {
        getCategories()
    }

    fun updateQuery(value: String) {
        _query.value = value
    }

    fun searchCategory() {
        viewModelScope.launch(Dispatchers.Default) {
           (categoryState.value as? CategoryUIState.Success)?.data?.let { data ->
               _searchResult.value = data.filter {
                   it.name.lowercase().contains(query.value.lowercase())
               }
           }
        }
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            accountId.collect { id ->
                if (id == null) {
                    _categoryState.value = CategoryUIState.Success(listOf())
                }
                else {
                    getAllCategory.execute().onSuccess {
                        _categoryState.value = CategoryUIState.Success(it)
                    }.onFailure {
                        _categoryState.value = CategoryUIState.Error(it)
                    }
                }

                cancel()
            }
        }
    }
}