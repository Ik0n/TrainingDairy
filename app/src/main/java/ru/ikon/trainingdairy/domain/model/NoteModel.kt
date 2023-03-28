package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.ikon.trainingdairy.domain.repository.room.DateConverter
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись - заметку
 */
@Entity(tableName = "notes")
@TypeConverters(DateConverter::class)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,

    override var date: Date,
    var text: String? = null) :
    DiaryEntryModel(id, date)