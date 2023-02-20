package ru.ikon.trainingdairy.domain.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Класс, представляющий собой дневниковую запись о тренировке
 */
data class TrainingModel(override val date: Date?, val text: String? = null) : DiaryEntryModel(date) {
    /** Название тренировки */
    var name: String? = null

    /** Комментарий к тренировке  */
    var comment: String? = null

    /** Список упражнений для этой тренировки */
    var exerciseList: ArrayList<ExerciseModel> = ArrayList()
}