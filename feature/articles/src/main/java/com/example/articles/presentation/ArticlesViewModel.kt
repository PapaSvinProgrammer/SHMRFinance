package com.example.articles.presentation

import androidx.lifecycle.ViewModel
import com.example.articles.domain.SearchCategory
import com.example.category.GetAllCategory
import com.example.model.Category
import com.example.ui.uiState.CategoryUIState
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val SEARCH_JOB = "search"
private const val GET_CATEGORIES_JOB = "get_categories"

internal class ArticlesViewModel @Inject constructor(
    private val getAllCategory: GetAllCategory,
    private val searchCategory: SearchCategory
): ViewModel() {
    private val _categoryState = MutableStateFlow(CategoryUIState.Loading as CategoryUIState)
    private val _searchResult = MutableStateFlow<List<Category>>(listOf())
    private val _query = MutableStateFlow("")

    val categoryState = _categoryState.asStateFlow()
    val searchResult = _searchResult.asStateFlow()
    val query = _query.asStateFlow()

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
        getAllCategory.execute(Unit).onSuccess {
            _categoryState.value = CategoryUIState.Success(it)
        }.onFailure {
            _categoryState.value = CategoryUIState.Error(it)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }
}