package com.example.toyproject_note.domain.model

data class NoteData(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)