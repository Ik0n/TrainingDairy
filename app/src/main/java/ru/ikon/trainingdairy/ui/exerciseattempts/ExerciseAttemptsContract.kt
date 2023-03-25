package ru.ikon.trainingdairy.ui.exerciseattempts

import ru.ikon.trainingdairy.domain.model.AttemptModel

class ExerciseAttemptsContract {

    interface View {
        fun showAttempts(data: List<AttemptModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(trainingId: Long, exerciseId: Long)
        fun detach()
        fun getExerciseName(trainingId: Long, exerciseId: Long): String
        fun onAttemptDeleted(trainingId: Long, exerciseId: Long, attemptId: Long)
    }
}