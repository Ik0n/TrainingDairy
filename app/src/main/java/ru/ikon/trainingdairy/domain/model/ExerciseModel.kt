package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой упражнение
 */
data class ExerciseModel(val name: String?) {
    /** ID упражнения */
    var id: Long = 0

    /** ID тренировки */
    var trainingId: Long = 0

    /** Признак того, что упражнение отмечено в списке */
    var isChecked = false

    /** Дата для отображения в истории */
    var date: Date? = null

    /** Список подходов для этого упражнения */
    var attemptsList: ArrayList<AttemptModel> = ArrayList()
}