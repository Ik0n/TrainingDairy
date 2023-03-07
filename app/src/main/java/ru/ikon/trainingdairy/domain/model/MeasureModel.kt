package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись об измерении
 */
class MeasureModel(date: Date) : DiaryEntryModel(date) {
    /** Список параметров для этого измерения */
    var parametersList: ArrayList<ParameterModel> = ArrayList()
}