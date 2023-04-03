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


    fun addNote(date: Date, text: String)
    fun getNote(id: Long) : NoteModel
    fun updateNote(id: Long, date: Date, text: String)
    fun deleteNote(id: Long)

    fun getTraining(id: Long): TrainingModel
    fun addTraining(name: String, date: Date, comment: String) : Long
    fun updateTraining(id: Long, name: String, date: Date, comment: String)
    fun deleteTraining(id: Long)

    fun getParameters(id: Long) : List<ParameterModel>
    fun deleteParameter(parameterId: Long, measureId: Long)
    fun updateParameters(measureId: Long, list: List<ParameterModel>)

    fun getMeasure(id: Long): MeasureModel
    fun addMeasure(date: Date, comment: String): Long
    fun updateMeasure(id: Long, date: Date, comment: String)
    fun deleteMeasure(id: Long)

    fun getExercises(trainingId: Long): List<ExerciseModel>
    fun updateExercises(trainingId: Long, exerciseList: ArrayList<ExerciseModel>)
    fun deleteExercise(exerciseId: Long, trainingId: Long)
    fun getExercise(trainingId: Long, exerciseId: Long): ExerciseModel

    fun getAttempts(trainingId: Long,exerciseId: Long): List<AttemptModel>
    fun getAttempt(trainingId: Long, exerciseId: Long, attemptId: Long): AttemptModel
    fun addAttempt(trainingId: Long, exerciseId: Long, weight: Int, count: Int)
    fun updateAttempt(trainingId: Long, exerciseId: Long, attemptId: Long, weight: Int, count: Int)
    fun deleteAttempt(trainingId: Long, exerciseId: Long, attemptId: Long)

    fun getHistory(exerciseName: String): List<ExerciseModel>
}