package ru.ikon.trainingdairy.ui.exercise

import ru.ikon.trainingdairy.domain.model.ExerciseModel

class ExerciseContract {

    interface View {
        fun showData(data: List<ExerciseModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(trainingId: Long)
        fun detach()
        fun saveExercises(trainingId: Long, exerciseList: ArrayList<ExerciseModel>)
    }
}