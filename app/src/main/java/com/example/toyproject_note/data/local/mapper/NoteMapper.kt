package com.example.toyproject_note.data.local.mapper

import com.example.toyproject_note.data.local.entity.NoteEntity
import com.example.toyproject_note.domain.model.NoteData

fun NoteEntity.toDomain(): NoteData {
    return NoteData(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
    )
}

fun NoteData.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt
    )
}