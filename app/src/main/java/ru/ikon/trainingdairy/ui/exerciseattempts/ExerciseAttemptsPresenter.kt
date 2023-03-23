package ru.ikon.trainingdairy.ui.exerciseattempts

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class ExerciseAttemptsPresenter : ExerciseAttemptsContract.Presenter {

    var view: ExerciseAttemptsContract.View? = null

    override fun attach(view: ExerciseAttemptsContract.View) {
        this.view = view
    }

    override fun onCreate(trainingId: Long, exerciseId: Long) {
        view?.showData(DummyDiaryEntryRepositoryImpl.newInstance().getAttempts(trainingId, exerciseId))
    }

    override fun detach() {
        this.view = null
    }

    override fun getExerciseName(trainingId: Long, exerciseId: Long): String {
        return DummyDiaryEntryRepositoryImpl.newInstance().getExercise(trainingId, exerciseId).name
    }

    override fun deleteAttempt(trainingId: Long, exerciseId: Long, attemptId: Long) {
        DummyDiaryEntryRepositoryImpl.newInstance().deleteAttempt(trainingId, exerciseId, attemptId)
    }
}