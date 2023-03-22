package ru.ikon.trainingdairy.ui.exercise

import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.ui.measure.MeasureContract
import java.util.*
import kotlin.collections.ArrayList

class ExerciseContract {

    interface View {
        fun showData(data: List<ExerciseModel>)
    }

    interface Presenter {
        fun attach(view: ExerciseContract.View)
        fun onCreate(trainingId: Long)
        fun detach()
        fun saveExercises(trainingId: Long, exerciseList: ArrayList<ExerciseModel>)
    }
}