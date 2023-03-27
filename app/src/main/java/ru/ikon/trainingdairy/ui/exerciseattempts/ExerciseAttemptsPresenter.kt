package ru.ikon.trainingdairy.ui.exerciseattempts

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter

class ExerciseAttemptsPresenter(repository: DiaryEntryRepository) : ExerciseAttemptsContract.Presenter, BasePresenter<ExerciseAttemptsContract.View>(
    repository
) {

    override fun onCreate(trainingId: Long, exerciseId: Long) {
        view?.showAttempts(repository.getAttempts(trainingId, exerciseId))
    }

    override fun getExerciseName(trainingId: Long, exerciseId: Long): String {
        return repository.getExercise(trainingId, exerciseId).name
    }

    override fun onAttemptDeleted(trainingId: Long, exerciseId: Long, attemptId: Long) {
        repository.deleteAttempt(trainingId, exerciseId, attemptId)
        view?.showAttempts(repository.getAttempts(trainingId, exerciseId))
    }
}