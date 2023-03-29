package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс, представляющий собой запись о подходе
 */
@Entity(tableName = "attempts")
data class AttemptModel(var weight: Int, var count: Int) {
    /** ID подхода  */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    /** ID упражнения, к которому относится данный подход */
    var exerciseId: Long = 0
}