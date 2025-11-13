package com.example.toyproject_note.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyproject_note.domain.model.AddMemoUiState
import com.example.toyproject_note.domain.usecase.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AddMemoUiState())
    val uiState = _uiState.asStateFlow()

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(
                text = text,
                showTitleError = false
            )
        }
    }

    fun saveMemo() {
        val lines = _uiState.value.text.lines()
        val title = lines.firstOrNull()?.trim() ?: ""
        val content = lines.drop(1).joinToString("\n")

        if (title.isBlank()) {
            _uiState.update { it.copy(showTitleError = true) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            addNoteUseCase(title, content)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isSaving = false,
                            saveSuccess = true
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isSaving = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun resetSaveSuccess() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}