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
            TrainingModel(1,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(2,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    4
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(3,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    6
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(4,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    8
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(5,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    11
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(6,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    13
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(7,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    15
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(8,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    18
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(9,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    20
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(10,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    22
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(11,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    25
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(12,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    27
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            MeasureModel(1,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time
            )
        )
        entriesList.add(
            MeasureModel(2,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    8
                ).time
            )
        )
        entriesList.add(
            MeasureModel(3,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    15
                ).time
            )
        )
        entriesList.add(
            MeasureModel(4,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    22
                ).time
            )
        )
        entriesList.add(
            NoteModel(1,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    1
                ).time, "Заметка от 1 июня"
            )
        )
        entriesList.add(
            NoteModel(2,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    14
                ).time, "Заметка от 14 июня"
            )
        )
        entriesList.add(
            NoteModel(3,
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    27
                ).time, "Заметка от 27 июня"
            )
        )



        entriesList.add(
            TrainingModel(13,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(14,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    4
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(15,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    6
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(16,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    8
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(17,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    11
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(18,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    13
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(19,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    15
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(20,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    18
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(21,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    20
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(22,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    22
                ).time, "Руки"
            )
        )
        entriesList.add(
            TrainingModel(23,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    25
                ).time, "Ноги/плечи"
            )
        )
        entriesList.add(
            TrainingModel(24,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    27
                ).time, "Грудь/спина"
            )
        )
        entriesList.add(
            TrainingModel(25,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    29
                ).time, "Руки"
            )
        )

        val measure1 = MeasureModel(5,
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

        val measure2 = MeasureModel(6,
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

        val measure3 = MeasureModel(7,
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

        val measure4 = MeasureModel(8,
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

        val measure5 = MeasureModel(9,
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
            NoteModel(4,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time, "Заметка от 1 июня"
            )
        )
        entriesList.add(
            NoteModel(5,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    14
                ).time, "Заметка от 14 июня"
            )
        )
        entriesList.add(
            NoteModel(6,
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
        return entriesList.find { x -> (x is NoteModel && x.id == id) } as NoteModel
    }

    override fun getTraining(id: Long): TrainingModel {
        return entriesList.find { x -> (x is TrainingModel && x.id == id) } as TrainingModel
    }
}