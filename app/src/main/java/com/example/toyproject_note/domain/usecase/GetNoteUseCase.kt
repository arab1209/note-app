package com.example.toyproject_note.domain.usecase

import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository
){
    operator fun invoke(): Flow<List<NoteData>> = repository.getAllMemos()
}