package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись о тренировке
 */
data class TrainingModel(override val id: Long = 0, override var date: Date, val text: String? = null) : DiaryEntryModel(id, date) {
    constructor(date: Date) : this(0, date)

    /** Название тренировки */
    var name: String? = null

    /** Комментарий к тренировке  */
    var comment: String? = null

    /** Список упражнений для этой тренировки */
    var exerciseList: ArrayList<ExerciseModel> = ArrayList()
}