package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись об измерении
 */
class MeasureModel(override val id: Long, date: Date) : DiaryEntryModel(id, date) {
    /** Список параметров для этого измерения */
    var comment: String = ""
    var parametersList: ArrayList<ParameterModel> = ArrayList()
}