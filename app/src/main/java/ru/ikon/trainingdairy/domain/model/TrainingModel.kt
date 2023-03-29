package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.ikon.trainingdairy.domain.repository.room.DateConverter
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись о тренировке
 */
@Entity(tableName = "trainings")
@TypeConverters(DateConverter::class)
data class TrainingModel(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    override var date: Date,
    var name: String? = null) : DiaryEntryModel(id, date) {

    /** Комментарий к тренировке  */
    var comment: String? = null

    /** Список упражнений для этой тренировки */
    @Ignore
    var exerciseList: ArrayList<ExerciseModel> = ArrayList<ExerciseModel>()
}