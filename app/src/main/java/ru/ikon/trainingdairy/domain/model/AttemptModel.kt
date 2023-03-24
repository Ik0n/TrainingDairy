package ru.ikon.trainingdairy.domain.model

/**
 * Класс, представляющий собой запись о подходе
 */
data class AttemptModel(var weight: Int, var count: Int) {
    /** ID подхода  */
    var id: Long = 0

    /** ID упражнения, к которому относится данный подход */
    var exerciseId: Long = 0
}