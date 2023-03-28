package ru.ikon.trainingdairy.domain.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: String,
    val date: Date,
    val text: String
)