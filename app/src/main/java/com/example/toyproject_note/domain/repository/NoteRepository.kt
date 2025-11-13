package com.example.toyproject_note.domain.repository

import com.example.toyproject_note.domain.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addMemo(memo: NoteData)
    fun getAllMemos(): Flow<List<NoteData>>
}