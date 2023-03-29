package ru.ikon.trainingdairy.domain.repository.room

import androidx.room.*
import ru.ikon.trainingdairy.domain.model.*
import java.util.*

@Dao
interface DiaryEntryDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): List<NoteModel>

    @Query("SELECT * FROM notes WHERE date = :date")
    fun getNotes(date: Long): List<NoteModel>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: Long): NoteModel

    @Insert
    fun insertNote(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)



    @Query("SELECT * FROM measures")
    fun getMeasures(): List<MeasureModel>

    @Query("SELECT * FROM measures WHERE date = :date")
    fun getMeasures(date: Long): List<MeasureModel>

    @Query("SELECT * FROM measures WHERE id = :id")
    fun getMeasure(id: Long): MeasureModel

    @Insert
    fun insertMeasure(measure: MeasureModel): Long

    @Update
    fun updateMeasure(measure: MeasureModel)

    @Delete
    fun deleteMeasure(measure: MeasureModel)



    @Query("SELECT * FROM parameters WHERE measureId = :id")
    fun getParameters(id: Long): List<ParameterModel>

    @Query("SELECT * FROM parameters WHERE id = :id")
    fun getParameter(id: Long): ParameterModel

    @Query("DELETE FROM parameters WHERE measureId = :measureId")
    fun deleteParameters(measureId: Long)

    @Delete
    fun deleteParameter(parameter: ParameterModel)

    @Insert
    fun insertParameter(parameter: ParameterModel): Long



    @Query("SELECT * FROM exercises WHERE trainingId = :trainingId")
    fun getExercises(trainingId: Long): List<ExerciseModel>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getExercise(id: Long): ExerciseModel

    @Query("DELETE FROM exercises WHERE trainingId = :trainingId")
    fun deleteExercises(trainingId: Long)

    @Delete
    fun deleteExercise(exercise: ExerciseModel)

    @Insert
    fun insertExercise(exercise: ExerciseModel)



    @Query("SELECT * FROM attempts WHERE exerciseId = :exerciseId")
    fun getAttempts(exerciseId: Long): List<AttemptModel>

    @Query("SELECT * FROM attempts WHERE id = :id")
    fun getAttempt(id: Long): AttemptModel

    @Insert
    fun insertAttempt(attempt: AttemptModel)

    @Update
    fun updateAttempt(attempt: AttemptModel)

    @Delete
    fun deleteAttempt(attempt: AttemptModel)



    @Query("SELECT * FROM trainings")
    fun getTrainings(): List<TrainingModel>

    @Query("SELECT * FROM trainings WHERE date = :date")
    fun getTrainings(date: Long): List<TrainingModel>

    @Query("SELECT * FROM trainings WHERE id = :id")
    fun getTraining(id: Long): TrainingModel

    @Insert
    fun insertTraining(training: TrainingModel): Long

    @Update
    fun updateTraining(training: TrainingModel)

    @Delete
    fun deleteTraining(training: TrainingModel)
}