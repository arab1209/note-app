package com.example.toyproject_note.domain.usecase

import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteMemoUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(memo: NoteData) {
        repository.deleteMemo(memo)
    }
}