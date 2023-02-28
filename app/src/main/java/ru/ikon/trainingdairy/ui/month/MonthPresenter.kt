package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import java.util.*
import kotlin.collections.ArrayList

class MonthPresenter : MonthContract.Presenter {
    private var view: MonthContract.View? = null

    override fun attach(view: MonthContract.View) {
        this.view = view
    }

    override fun onCreate() {
        // Создаём список с тестовыми данными. Позднее здесь будет загрузка данных из базы
        val entriesList = generateDummyData()

        // Передаём эти данные во View и просим отобразить их в календаре
        view?.showData(entriesList)
    }

    override fun detach() {
        view = null
    }

    /**
     * Создаёт список с тестовыми данными
     */
    private fun generateDummyData(): ArrayList<DiaryEntryModel> {
        val entriesList = ArrayList<DiaryEntryModel>()

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
            TrainingModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    29
                ).time, "Руки"
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
            MeasureModel(
                GregorianCalendar(
                    2023,
                    Calendar.FEBRUARY,
                    29
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

        return entriesList
    }
}