package com.example.toyproject_note.domain.usecase

import com.example.toyproject_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetMemoByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(id: Long) = repository.getNoteById(id)
}