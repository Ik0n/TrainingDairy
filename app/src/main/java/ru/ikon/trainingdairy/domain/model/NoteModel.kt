package ru.ikon.trainingdairy.domain.model

import ru.ikon.trainingdairy.ui.day.recycler.TYPE_NOTE
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись - заметку
 */
data class NoteModel(override val date: Date?, val text: String? = null) :
    DiaryEntryModel(date)