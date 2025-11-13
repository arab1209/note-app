package com.example.toyproject_note.domain.usecase

import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val memoRepository: NoteRepository
) {
    suspend operator fun invoke(title: String, content: String): Result<Unit> {
        return try {
            if (title.isBlank()) {
                Result.failure(Exception("제목을 입력해주세요"))
            } else {
                val memo = NoteData(
                    title = title,
                    content = content,
                    createdAt = System.currentTimeMillis()
                )
                memoRepository.addMemo(memo)
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}