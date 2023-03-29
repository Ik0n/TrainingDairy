package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс, представляющий собой запись о подходе
 */
@Entity(tableName = "attempts")
data class AttemptModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /** ID упражнения, к которому относится данный подход */
    var exerciseId: Long = 0,
    var weight: Int,
    var count: Int)