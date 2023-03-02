package ru.ikon.trainingdairy.domain.repository

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel

/**
 * Интерфейс репозитория, в котором объявлены методы доступа к данным (дневниковым записям)
 */
interface DiaryEntryRepository {
    /**
     * Возвращает все дневниковые записи
     */
    fun getEntries() : List<DiaryEntryModel>
}