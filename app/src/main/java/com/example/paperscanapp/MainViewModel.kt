package com.example.paperscanapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
    val isEditModeEnabled: Boolean = false
)

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun toggleEditMode() = viewModelScope.launch {
        _uiState.update {
            it.copy(isEditModeEnabled = !it.isEditModeEnabled)
        }
    }
}