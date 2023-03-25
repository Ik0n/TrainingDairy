package ru.ikon.trainingdairy.ui.exerciseattempts

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class ExerciseAttemptsPresenter : ExerciseAttemptsContract.Presenter {

    var view: ExerciseAttemptsContract.View? = null
    private val repository = DummyDiaryEntryRepositoryImpl.newInstance()

    override fun attach(view: ExerciseAttemptsContract.View) {
        this.view = view
    }

    override fun onCreate(trainingId: Long, exerciseId: Long) {
        view?.showAttempts(repository.getAttempts(trainingId, exerciseId))
    }

    override fun detach() {
        this.view = null
    }

    override fun getExerciseName(trainingId: Long, exerciseId: Long): String {
        return repository.getExercise(trainingId, exerciseId).name
    }

    override fun onAttemptDeleted(trainingId: Long, exerciseId: Long, attemptId: Long) {
        repository.deleteAttempt(trainingId, exerciseId, attemptId)
        view?.showAttempts(repository.getAttempts(trainingId, exerciseId))
    }
}