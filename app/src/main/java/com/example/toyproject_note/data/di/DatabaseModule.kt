package com.example.toyproject_note.data.di

import android.content.Context
import androidx.room.Room
import com.example.toyproject_note.data.local.dao.NoteDao
import com.example.toyproject_note.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "memo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMemoDao(database: AppDatabase): NoteDao {
        return database.memoDao()
    }
}