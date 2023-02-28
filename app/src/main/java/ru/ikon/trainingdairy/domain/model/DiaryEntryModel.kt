package ru.ikon.trainingdairy.domain.model

import ru.ikon.trainingdairy.ui.day.recycler.TYPE_NOTE
import java.util.*

/**
 * Класс, представляющий собой дневниковую запись
 */
open class DiaryEntryModel(open val date: Date?, open var type: Int) {

    /** ID карточки */
    var id: Long = 0



}