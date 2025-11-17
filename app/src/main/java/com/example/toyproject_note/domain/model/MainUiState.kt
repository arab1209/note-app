package com.example.toyproject_note.domain.model

data class MainUiState(
    val memoList: List<NoteData> = emptyList(),
    val isEditMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)