package ru.ikon.trainingdairy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.ikon.trainingdairy.domain.repository.room.DateConverter

/**
 * Класс, представляющий собой модель параметра - вес, рост, талия и т.д.
 */
@Entity(tableName = "parameters")
@TypeConverters(DateConverter::class)
data class ParameterModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val measureId: Long,
    val name: String,
    val value: Int)