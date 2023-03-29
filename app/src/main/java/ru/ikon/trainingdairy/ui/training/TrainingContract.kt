package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import java.util.*

class TrainingContract {
    interface View {
        fun showData(training: TrainingModel)
        fun showExercises(exerciseList: List<ExerciseModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long, date: Date)
        fun getTraining(trainingId: Long): TrainingModel
        fun detach()
        fun onSaveTraining(trainingId: Long, name: String, date: Date, comment: String) : Long
        fun updateTraining(id: Long, name: String, date: Date, comment: String)
        fun onExerciseDeleted(exerciseId: Long, trainingId: Long)
    }
}