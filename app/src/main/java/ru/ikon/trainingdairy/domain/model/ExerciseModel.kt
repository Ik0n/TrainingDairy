package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.ikon.trainingdairy.domain.repository.room.DateConverter
import java.util.*

/**
 * Класс, представляющий собой упражнение
 */
@Entity(tableName = "exercises")
@TypeConverters(DateConverter::class)
data class ExerciseModel(val name: String) {
    /** ID упражнения */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    /** ID тренировки */
    var trainingId: Long = 0

    /** Признак того, что упражнение отмечено в списке */
    var isChecked = false

    /** Дата для отображения в истории */
    var date: Date? = null

    /** Список подходов для этого упражнения */
    @Ignore
    var attemptsList: ArrayList<AttemptModel> = ArrayList()
}