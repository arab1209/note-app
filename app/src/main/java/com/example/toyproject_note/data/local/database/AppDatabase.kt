package com.example.toyproject_note.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toyproject_note.data.local.dao.NoteDao
import com.example.toyproject_note.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun memoDao(): NoteDao
}