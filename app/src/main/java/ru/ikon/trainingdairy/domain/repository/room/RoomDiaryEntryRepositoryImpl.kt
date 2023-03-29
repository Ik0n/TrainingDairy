package ru.ikon.trainingdairy.domain.repository.room

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository

import ru.ikon.trainingdairy.domain.model.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 * Репозиторий, работающий с базой данных Room.
 */
class RoomDiaryEntryRepositoryImpl(
    private val dao: DiaryEntryDao
) : DiaryEntryRepository {

    companion object {
        @JvmStatic
        private val entriesList: ArrayList<DiaryEntryModel> = ArrayList()

        @JvmStatic
        private val exerciseList: ArrayList<ExerciseModel> = ArrayList()

        init {
            // Создадим несколько записей об измерениях, тренировках и заметках
            entriesList.add(
                TrainingModel(
                    1,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        1
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    2,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        4
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    3,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        6
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    4,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        8
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    5,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        11
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    6,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        13
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    7,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        15
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    8,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        18
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    9,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        20
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    10,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        22
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    11,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        25
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    12,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        27
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                MeasureModel(
                    1,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        1
                    ).time
                )
            )
            entriesList.add(
                MeasureModel(
                    2,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        8
                    ).time
                )
            )
            entriesList.add(
                MeasureModel(
                    3,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        15
                    ).time
                )
            )
            entriesList.add(
                MeasureModel(
                    4,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        22
                    ).time
                )
            )
            entriesList.add(
                NoteModel(
                    1,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        1
                    ).time, "Заметка от 1 июня"
                )
            )
            entriesList.add(
                NoteModel(
                    2,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        14
                    ).time, "Заметка от 14 июня"
                )
            )
            entriesList.add(
                NoteModel(
                    3,
                    GregorianCalendar(
                        2023,
                        Calendar.FEBRUARY,
                        27
                    ).time, "Заметка от 27 июня"
                )
            )


            val trainingModel1 = TrainingModel(
                13,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time, "Руки"
            )
            trainingModel1.comment = "Сегодня первый день весны!"
            trainingModel1.exerciseList =
                arrayListOf(ExerciseModel("Выпады с гантелями").apply {
                    trainingId = 13
                    isChecked = true
                    date = trainingModel1.date
                    id = 1
                    attemptsList = arrayListOf(AttemptModel(60, 20).apply {
                        exerciseId = 1
                        id = 1

                    })
                })
            entriesList.add(
                trainingModel1
            )
            entriesList.add(
                TrainingModel(
                    14,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        4
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    15,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        6
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    16,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        8
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    17,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        11
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    18,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        13
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    19,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        15
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    20,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        18
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    21,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        20
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    22,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        22
                    ).time, "Руки"
                )
            )
            entriesList.add(
                TrainingModel(
                    23,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        25
                    ).time, "Ноги/плечи"
                )
            )
            entriesList.add(
                TrainingModel(
                    24,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        27
                    ).time, "Грудь/спина"
                )
            )
            entriesList.add(
                TrainingModel(
                    25,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        29
                    ).time, "Руки"
                )
            )

            val measure1 = MeasureModel(
                5,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    1
                ).time
            )
            measure1.parametersList.add(ParameterModel(0, measure1.id, "Вес (кг)", 64))
            measure1.parametersList.add(ParameterModel(1, measure1.id, "Грудь (см)", 89))
            measure1.parametersList.add(ParameterModel(2, measure1.id, "Талия (см)", 59))
            measure1.parametersList.add(ParameterModel(3, measure1.id, "Бёдра (см)", 89))
            entriesList.add(measure1)

            val measure2 = MeasureModel(
                6,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    8
                ).time
            )
            measure2.parametersList.add(ParameterModel(4, measure2.id, "Вес (кг)", 63))
            measure2.parametersList.add(ParameterModel(5, measure2.id, "Грудь (см)", 88))
            measure2.parametersList.add(ParameterModel(6, measure2.id, "Талия (см)", 58))
            measure2.parametersList.add(ParameterModel(7, measure2.id, "Бёдра (см)", 88))
            entriesList.add(measure2)

            val measure3 = MeasureModel(
                7,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    15
                ).time
            )
            measure3.parametersList.add(ParameterModel(8, measure3.id, "Вес (кг)", 62))
            measure3.parametersList.add(ParameterModel(9, measure3.id, "Грудь (см)", 87))
            measure3.parametersList.add(ParameterModel(10, measure3.id, "Талия (см)", 57))
            measure3.parametersList.add(ParameterModel(11, measure3.id, "Бёдра (см)", 87))
            entriesList.add(measure3)

            val measure4 = MeasureModel(
                8,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    22
                ).time
            )
            measure4.parametersList.add(ParameterModel(12, measure4.id, "Вес (кг)", 61))
            measure4.parametersList.add(ParameterModel(13, measure4.id, "Грудь (см)", 86))
            measure4.parametersList.add(ParameterModel(14, measure4.id, "Талия (см)", 56))
            measure4.parametersList.add(ParameterModel(15, measure4.id, "Бёдра (см)", 86))
            entriesList.add(measure4)

            val measure5 = MeasureModel(
                9,
                GregorianCalendar(
                    2023,
                    Calendar.MARCH,
                    22
                ).time
            )
            measure5.parametersList.add(ParameterModel(16, measure5.id, "Вес (кг)", 60))
            measure5.parametersList.add(ParameterModel(17, measure5.id, "Грудь (см)", 85))
            measure5.parametersList.add(ParameterModel(18, measure5.id, "Талия (см)", 55))
            measure5.parametersList.add(ParameterModel(19, measure5.id, "Бёдра (см)", 85))
            entriesList.add(measure5)

            entriesList.add(
                NoteModel(
                    4,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        1
                    ).time, "Заметка от 1 июня"
                )
            )
            entriesList.add(
                NoteModel(
                    5,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        14
                    ).time, "Заметка от 14 июня"
                )
            )
            entriesList.add(
                NoteModel(
                    6,
                    GregorianCalendar(
                        2023,
                        Calendar.MARCH,
                        27
                    ).time, "Заметка от 27 июня"
                )
            )

            exerciseList.add(ExerciseModel("Выпады с гантелями"))
            exerciseList.add(ExerciseModel("Жим гантелей сидя"))
            exerciseList.add(ExerciseModel("Жим ногами"))
            exerciseList.add(ExerciseModel("Разведение гантелей стоя"))

        }
    }

    override fun getEntries(): List<DiaryEntryModel> {
        val result = ArrayList<DiaryEntryModel>()
        result.addAll(dao.getNotes())
        result.addAll(dao.getMeasures())
        result.addAll(dao.getTrainings())
        return result
    }

    override fun getEntries(date: Date): List<DiaryEntryModel> {
        val result = ArrayList<DiaryEntryModel>()
        result.addAll(dao.getNotes(date.time))

        val measures = dao.getMeasures(date.time)

        measures.forEach {
            addParametersToMeasure(it)
        }

        result.addAll(measures)
        result.addAll(dao.getTrainings(date.time))
        return result
    }

    private fun addParametersToMeasure(measure: MeasureModel) {
        val parameters = getParameters(measure.id)
        measure.parametersList.addAll(parameters)
    }

    override fun addNote(date: Date, text: String) {
        val noteToInsert = NoteModel(0, date, text)
        dao.insertNote(noteToInsert)
    }

    override fun getNote(id: Long): NoteModel {
        return dao.getNote(id)
    }

    override fun updateNote(id: Long, date: Date, text: String) {
        val noteToUpdate = getNote(id)
        noteToUpdate.date = date
        noteToUpdate.text = text
        dao.updateNote(noteToUpdate)
    }

    override fun deleteNote(id: Long) {
        entriesList.remove(getNote(id))
    }

    override fun getTraining(id: Long): TrainingModel {
        return dao.getTraining(id)
    }

    override fun addTraining(name: String, date: Date, comment: String): Long {
        val trainingToAdd = TrainingModel(0, date, comment)
        return dao.insertTraining(trainingToAdd)
    }

    override fun updateTraining(
        id: Long,
        name: String,
        date: Date,
        comment: String
    ) {
        val trainingToUpdate = getTraining(id)
        trainingToUpdate.name = name
        trainingToUpdate.date = date
        trainingToUpdate.comment = comment
        dao.updateTraining(trainingToUpdate)
    }

    override fun deleteTraining(id: Long) {
        val trainingToRemove = getTraining(id)
        dao.deleteTraining(trainingToRemove)
    }


    override fun getParameters(measureId: Long): List<ParameterModel> {
        return dao.getParameters(measureId)
        //return ArrayList<ParameterModel>() // TODO: Реализовать как надо!
    }

    override fun deleteParameter(parameterId: Long, measureId: Long) {
        val parameterToDelete = dao.getParameter(parameterId)
        dao.deleteParameter(parameterToDelete)
    }

    override fun updateParameters(measureId: Long, list: List<ParameterModel>) {
        dao.deleteParameters(measureId)
        list.forEach {
            dao.insertParameter(it)
        }
    }

    override fun getMeasure(id: Long): MeasureModel {
        val measure = dao.getMeasure(id)
        val parametersList = dao.getParameters(id)
        measure.parametersList.addAll(parametersList)
        return measure
    }

    override fun addMeasure(date: Date, comment: String): Long {
        val measure = MeasureModel(0, date)
        measure.comment = comment
        return dao.insertMeasure(measure)
    }

    override fun updateMeasure(id: Long, date: Date, comment: String) {
        val measureToUpdate = getMeasure(id)
        measureToUpdate.date = date
        measureToUpdate.comment = comment
        dao.updateMeasure(measureToUpdate)
    }

    override fun deleteMeasure(id: Long) {
        dao.deleteMeasure(getMeasure(id))
    }

    override fun getExercises(trainingId: Long): List<ExerciseModel> {
        return dao.getExercises(trainingId)
    }

    override fun addExercises(
        trainingId: Long,
        exerciseList: ArrayList<ExerciseModel>
    ) {
        // TODO: Доделать!
        getTraining(trainingId).exerciseList = exerciseList
    }

    override fun deleteExercise(exerciseId: Long, trainingId: Long) {
        val exerciseToDelete = getExercise(exerciseId, trainingId)
        dao.deleteExercise(exerciseToDelete)
    }

    override fun getExercise(
        trainingId: Long,
        exerciseId: Long
    ): ExerciseModel {
        return dao.getExercise(exerciseId)
    }

    override fun getAttempts(
        trainingId: Long,
        exerciseId: Long
    ): List<AttemptModel> {
        return dao.getAttempts(exerciseId)
    }

    override fun getAttempt(
        trainingId: Long,
        exerciseId: Long,
        attemptId: Long
    ): AttemptModel {
        return dao.getAttempt(attemptId)
    }

    override fun addAttempt(
        trainingId: Long,
        exerciseId: Long,
        weight: Int,
        count: Int
    ) {
        val attemptToAdd = AttemptModel(weight, count)
        dao.insertAttempt(attemptToAdd)
        // TODO: Прикрепить к упражнению!
    }

    override fun updateAttempt(
        trainingId: Long,
        exerciseId: Long,
        attemptId: Long,
        weight: Int,
        count: Int
    ) {
        val attemptToUpdate = getAttempt(trainingId, exerciseId, attemptId)
        attemptToUpdate.weight = weight
        attemptToUpdate.count = count
        dao.updateAttempt(attemptToUpdate)
    }

    override fun deleteAttempt(
        trainingId: Long,
        exerciseId: Long,
        attemptId: Long
    ) {
        val attemptToDelete = getAttempt(trainingId, exerciseId, attemptId)
        dao.deleteAttempt(attemptToDelete)
    }

    override fun getHistory(exerciseName: String): List<ExerciseModel> {
        val results = ArrayList<ExerciseModel>()
        val trainingsList = entriesList.filterIsInstance<TrainingModel>()
        trainingsList.forEach { trainingModel ->
            (trainingModel.exerciseList.filter { exercise ->
                exercise.name.equals(
                    exerciseName
                )
            }).forEach { exerciseModel ->
                exerciseModel.date = trainingModel.date
                results.add(exerciseModel)
            }
        }
        return results
        // TODO: Реализовать!
    }
}