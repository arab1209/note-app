package com.example.toyproject_note.domain.model

data class AddMemoUiState(
    val text: String = "",
    val showTitleError: Boolean = false,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val errorMessage: String? = null
)
