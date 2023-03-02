package ru.ikon.trainingdairy.domain.repository

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Тестовая имплементация репозитория, которая содержит массив дневниковых записей, объявленных прямо в коде
 */
class DummyDiaryEntryRepositoryImpl : DiaryEntryRepository {
    private val entriesList : ArrayList<DiaryEntryModel> = ArrayList()

    init {
        // Создадим несколько записей об измерениях, тренировках и заметках
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    4
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    6
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    8
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    11
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    13
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    15
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    18
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    20
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    22
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    25
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    27
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    8
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    15
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    22
                ).time
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time, "Заметка от 1 июня"
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    14
                ).time, "Заметка от 14 июня"
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    27
                ).time, "Заметка от 27 июня"
            )
        )



        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    4
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    6
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    8
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    11
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    13
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    15
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    18
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    20
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    22
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    25
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    27
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    29
                ).time, "Руки"
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    8
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    15
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    22
                ).time
            )
        )
        entriesList.add(
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    29
                ).time
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time, "Заметка от 1 июня"
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    14
                ).time, "Заметка от 14 июня"
            )
        )
        entriesList.add(
            NoteModel(
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    27
                ).time, "Заметка от 27 июня"
            )
        )
    }

    override fun getEntries(): List<DiaryEntryModel> {
        return entriesList
    }
}