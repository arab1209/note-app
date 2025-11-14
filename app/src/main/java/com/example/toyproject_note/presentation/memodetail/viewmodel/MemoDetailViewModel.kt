package com.example.toyproject_note.presentation.memodetail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.usecase.GetMemoByIdUseCase
import com.example.toyproject_note.domain.usecase.UpdateMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoDetailViewModel @Inject constructor(
    private val getMemoByIdUseCase: GetMemoByIdUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase
): ViewModel() {

    private val memoFlows = mutableMapOf<Long, StateFlow<NoteData?>>()

    // 수정 모드 상태
    private val _isEditMode = MutableStateFlow(false)
    val isEditMode: StateFlow<Boolean> = _isEditMode.asStateFlow()

    // 수정 중인 텍스트
    private val _editTitle = MutableStateFlow("")
    val editTitle: StateFlow<String> = _editTitle.asStateFlow()

    private val _editContent = MutableStateFlow("")
    val editContent: StateFlow<String> = _editContent.asStateFlow()

    fun getMemoById(memoId: Long): StateFlow<NoteData?> {
        return memoFlows.getOrPut(memoId) {
            getMemoByIdUseCase(memoId)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = null
                )
        }
    }

    fun updateTitle(title: String) {
        _editTitle.value = title
    }

    fun updateContent(content: String) {
        _editContent.value = content
    }

    fun toggleEditMode(memo: NoteData) {
        if (_isEditMode.value) {
            viewModelScope.launch {
                updateMemoUseCase(NoteData(
                    memo.id, editTitle.value, editContent.value
                ))
            }
        } else {
            _editTitle.value = memo.title
            _editContent.value = memo.content
        }
        _isEditMode.value = !_isEditMode.value
    }
}