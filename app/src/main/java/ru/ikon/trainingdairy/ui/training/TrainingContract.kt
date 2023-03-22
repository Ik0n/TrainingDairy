package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.TrainingModel
import java.util.*

class TrainingContract {
    interface View {
        fun showData(data: TrainingModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long, date: Date)
        fun getTraining(trainingId: Long): TrainingModel
        fun detach()
        fun saveTraining(name: String, date: Date, comment: String) : Long
        fun updateTraining(id: Long, name: String, date: Date, comment: String)
        fun deleteExercise(exerciseId: Long, trainingId: Long)
    }
}