package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись
 */
open class DiaryEntryModel(open val date: Date?) {

    /** ID карточки */
    var id: Long = 0
}