package ru.ikon.trainingdairy.domain.repository

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
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
}