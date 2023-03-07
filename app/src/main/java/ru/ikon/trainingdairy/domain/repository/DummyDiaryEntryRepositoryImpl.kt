package ru.ikon.trainingdairy.domain.repository

import ru.ikon.trainingdairy.domain.model.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Тестовая имплементация репозитория, которая содержит массив дневниковых записей, объявленных прямо в коде
 */
class DummyDiaryEntryRepositoryImpl : DiaryEntryRepository {
    private val entriesList : ArrayList<DiaryEntryModel> = ArrayList()

    companion object {
        @JvmStatic
        fun newInstance() = DummyDiaryEntryRepositoryImpl()
    }

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

        val measure1 = MeasureModel(
            GregorianCalendar(
                2023,
                Calendar.MARCH,
                1
            ).time
        )
        measure1.parametersList.add(ParameterModel("Вес (кг)", 64))
        measure1.parametersList.add(ParameterModel("Грудь (см)", 89))
        measure1.parametersList.add(ParameterModel("Талия (см)", 59))
        measure1.parametersList.add(ParameterModel("Бёдра (см)", 89))
        entriesList.add(measure1)

        val measure2 = MeasureModel(
            GregorianCalendar(
                2023,
                Calendar.MARCH,
                8
            ).time
        )
        measure2.parametersList.add(ParameterModel("Вес (кг)", 63))
        measure2.parametersList.add(ParameterModel("Грудь (см)", 88))
        measure2.parametersList.add(ParameterModel("Талия (см)", 58))
        measure2.parametersList.add(ParameterModel("Бёдра (см)", 88))
        entriesList.add(measure2)

        val measure3 = MeasureModel(
            GregorianCalendar(
                2023,
                Calendar.MARCH,
                15
            ).time
        )
        measure3.parametersList.add(ParameterModel("Вес (кг)", 62))
        measure3.parametersList.add(ParameterModel("Грудь (см)", 87))
        measure3.parametersList.add(ParameterModel("Талия (см)", 57))
        measure3.parametersList.add(ParameterModel("Бёдра (см)", 87))
        entriesList.add(measure3)

        val measure4 = MeasureModel(
            GregorianCalendar(
                2023,
                Calendar.MARCH,
                22
            ).time
        )
        measure4.parametersList.add(ParameterModel("Вес (кг)", 61))
        measure4.parametersList.add(ParameterModel("Грудь (см)", 86))
        measure4.parametersList.add(ParameterModel("Талия (см)", 56))
        measure4.parametersList.add(ParameterModel("Бёдра (см)", 86))
        entriesList.add(measure4)

        val measure5 = MeasureModel(
            GregorianCalendar(
                2023,
                Calendar.MARCH,
                22
            ).time
        )
        measure5.parametersList.add(ParameterModel("Вес (кг)", 60))
        measure5.parametersList.add(ParameterModel("Грудь (см)", 85))
        measure5.parametersList.add(ParameterModel("Талия (см)", 55))
        measure5.parametersList.add(ParameterModel("Бёдра (см)", 85))
        entriesList.add(measure5)

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

    override fun getEntries(date: Date): List<DiaryEntryModel> {
        return entriesList.filter { x -> x.date == date }
    }

    override fun addNote(note: NoteModel) {
        entriesList.add(
            note
        )
    }

    override fun getNote(id: Long): NoteModel {
        return entriesList[id.toInt()] as NoteModel
    }
}