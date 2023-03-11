package ru.ikon.trainingdairy.domain.repository

import ru.ikon.trainingdairy.domain.model.*
import java.util.*

/**
 * Интерфейс репозитория, в котором объявлены методы доступа к данным (дневниковым записям)
 */
interface DiaryEntryRepository {
    /**
     * Возвращает все дневниковые записи
     */
    fun getEntries() : List<DiaryEntryModel>

    /**
     * Возвращает список дневниковых записей за указанную дату
     * @param date Дата
     * @return Список записей за эту дату     *
     */
    fun getEntries(date: Date) : List<DiaryEntryModel>

    fun addNote(note: NoteModel)
    fun getNote(id: Long) : NoteModel
    fun getTraining(id: Long): TrainingModel
    fun getParameters(id: Long) : List<ParameterModel>
    fun deleteParameter(parametrId: Long, measureId: Long)
}