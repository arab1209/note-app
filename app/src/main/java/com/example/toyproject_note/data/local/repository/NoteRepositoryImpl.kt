package com.example.toyproject_note.data.local.repository

import com.example.toyproject_note.data.local.dao.NoteDao
import com.example.toyproject_note.data.local.mapper.toDomain
import com.example.toyproject_note.data.local.mapper.toEntity
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun addMemo(memo: NoteData) {
        noteDao.addMemo(memo.toEntity())
    }

    override fun getAllMemos(): Flow<List<NoteData>> {
        return noteDao.getAllMemos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getNoteById(id: Long): Flow<NoteData> {
        return noteDao.getNoteById(id).map { entity ->
            entity.toDomain()
        }
    }

    override suspend fun updateMemo(memo: NoteData) {
        noteDao.updateMemo(memo.toEntity())
    }

    override suspend fun deleteMemo(memo: NoteData) {
        noteDao.deleteMemo(memo.toEntity())
    }
}