package com.example.toyproject_note.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.toyproject_note.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
   @Insert
   suspend fun addMemo(memo: NoteEntity)

   @Query("SELECT * FROM memos")
   fun getAllMemos(): Flow<List<NoteEntity>>

   @Query("SELECT * FROM memos WHERE id = :id")
   fun getNoteById(id: Long): Flow<NoteEntity>

   @Update
   suspend fun updateMemo(memo: NoteEntity)

   @Delete
   suspend fun deleteMemo(memo: NoteEntity)
}