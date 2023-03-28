package ru.ikon.trainingdairy.domain.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ikon.trainingdairy.domain.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class DiaryEntryDatabase : RoomDatabase() {
    abstract fun diaryEntryDao(): DiaryEntryDao
}