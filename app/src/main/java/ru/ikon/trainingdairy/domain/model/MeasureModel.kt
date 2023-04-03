package ru.ikon.trainingdairy.domain.model

import androidx.room.*
import ru.ikon.trainingdairy.domain.repository.room.DateConverter
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись об измерении
 */
@Entity(tableName = "measures")
@TypeConverters(DateConverter::class)
class MeasureModel(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,

    date: Date) : DiaryEntryModel(id, date) {

    var comment: String = ""

    /** Список параметров для этого измерения */
    @Ignore
    var parametersList: ArrayList<ParameterModel> = ArrayList()
}