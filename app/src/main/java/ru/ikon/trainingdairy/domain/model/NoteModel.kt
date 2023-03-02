package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись - заметку
 */
data class NoteModel(override val date: Date, val text: String? = null) :
    DiaryEntryModel(date)