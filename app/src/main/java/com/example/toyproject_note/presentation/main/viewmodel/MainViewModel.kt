package com.example.toyproject_note.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.usecase.AddNoteUseCase
import com.example.toyproject_note.domain.usecase.DeleteMemoUseCase
import com.example.toyproject_note.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getNoteUseCase: GetNoteUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
): ViewModel() {

    // 편집 모드 상태
    private val _isEditMode = MutableStateFlow(false)
    val isEditMode: StateFlow<Boolean> = _isEditMode.asStateFlow()

    // 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val memos: StateFlow<List<NoteData>> = getNoteUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteMemo(memo: NoteData) {
        viewModelScope.launch {
            deleteMemoUseCase(memo)
        }
    }

    fun updateEditMode(isEdit: Boolean) {
        _isEditMode.value = isEdit
    }

    fun toggleEditMode() {
        _isEditMode.value = !_isEditMode.value
    }

    fun finishEditMode() {
        _isEditMode.value = false
    }
}