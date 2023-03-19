package ru.ikon.trainingdairy.domain.model

import java.util.*

/**
 * Класс, представляющий собой дневниковую запись о тренировке
 */
data class TrainingModel(override val id: Long, override val date: Date, val name: String) : DiaryEntryModel(id, date) {
    /** Комментарий к тренировке  */
    var comment: String? = null

    /** Список упражнений для этой тренировки */
    var exerciseList: ArrayList<ExerciseModel> = ArrayList()
}