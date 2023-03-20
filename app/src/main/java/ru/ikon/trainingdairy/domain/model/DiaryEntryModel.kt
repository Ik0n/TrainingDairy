package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись
 */
open class DiaryEntryModel(open val id: Long, open var date: Date)