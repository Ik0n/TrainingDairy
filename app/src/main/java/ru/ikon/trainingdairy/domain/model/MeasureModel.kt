package ru.ikon.trainingdairy.domain.model

import ru.ikon.trainingdairy.ui.day.recycler.TYPE_MEASURE
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись об измерении
 */
class MeasureModel(date: Date?) : DiaryEntryModel(date, TYPE_MEASURE)