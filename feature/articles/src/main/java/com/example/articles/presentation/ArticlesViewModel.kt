package com.example.articles.presentation

import androidx.lifecycle.ViewModel
import com.example.articles.domain.SearchCategory
import com.example.category.GetAllCategory
import com.example.data.external.PreferencesRepository
import com.example.model.Category
import com.example.ui.uiState.CategoryUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val SEARCH_JOB = "search"
private const val GET_CATEGORIES_JOB = "get_categories"

class ArticlesViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val getAllCategory: GetAllCategory,
    private val searchCategory: SearchCategory
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

    fun searchCategory() = launchWithoutOld(
        key = SEARCH_JOB,
        dispatcher = Dispatchers.Default
    ) {
        (categoryState.value as? CategoryUIState.Success)?.data?.let { data ->
            _searchResult.value = searchCategory.execute(
                list = data,
                query = query.value
            )
        }
    }

    private fun getCategories() = launchWithoutOld(GET_CATEGORIES_JOB) {
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
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }
}