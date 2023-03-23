package ru.ikon.trainingdairy.ui.attempt

import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.ui.exerciseattempts.ExerciseAttemptsContract

class AttemptContract {

    interface View {
        fun showData(data: AttemptModel)
    }

    interface Presenter {
        fun attach(view: AttemptContract.View)
        fun onCreate(trainingId: Long, exerciseId: Long, attemptId: Long)
        fun detach()
        fun saveAttempt(trainingId: Long, exerciseId: Long, attemptId: Long, weight: Int, count: Int)
    }

}