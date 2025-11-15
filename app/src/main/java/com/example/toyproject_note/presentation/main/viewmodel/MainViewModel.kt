package com.example.toyproject_note.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.usecase.AddNoteUseCase
import com.example.toyproject_note.domain.usecase.DeleteMemoUseCase
import com.example.toyproject_note.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getNoteUseCase: GetNoteUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
): ViewModel() {

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
}