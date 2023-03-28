package ru.ikon.trainingdairy.domain.repository.room

import androidx.room.*
import ru.ikon.trainingdairy.domain.model.NoteModel
import java.util.*

@Dao
interface DiaryEntryDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): List<NoteModel>

    @Query("SELECT * FROM notes WHERE date = :date")
    fun getNotes(date: Long): List<NoteModel>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: Long): NoteModel

    @Insert
    fun insertNote(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)
}