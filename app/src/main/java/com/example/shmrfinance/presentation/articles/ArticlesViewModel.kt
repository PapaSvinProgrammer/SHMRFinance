package com.example.shmrfinance.presentation.articles

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    init {
        Log.d("RRRR", preferencesRepository.toString())
    }
}