package ru.ikon.trainingdairy.ui.history

import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.ui.training.TrainingContract
import java.util.*

class HistoryContract {
    interface View {
        fun showExercises(exerciseList: List<ExerciseModel>)
    }

    interface Presenter {
        fun attach(view: TrainingContract.View)
        fun onCreate(id: Long, date: Date)
        fun detach()
    }
}