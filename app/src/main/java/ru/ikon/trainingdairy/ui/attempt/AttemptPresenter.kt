package ru.ikon.trainingdairy.ui.attempt

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.base.BasePresenter

class AttemptPresenter(repository: DiaryEntryRepository) : AttemptContract.Presenter, BasePresenter<AttemptContract.View>(
    repository
) {

    override fun onCreate(trainingId: Long, exerciseId: Long, attemptId: Long) {
        if (attemptId != 0L) {
            view?.showData(DummyDiaryEntryRepositoryImpl.newInstance().getAttempt(trainingId, exerciseId, attemptId))
        }
    }

    override fun saveAttempt(
        trainingId: Long,
        exerciseId: Long,
        attemptId: Long,
        weight: Int,
        count: Int
    ) {
        if (attemptId != 0L) {
            DummyDiaryEntryRepositoryImpl.newInstance().updateAttempt(trainingId, exerciseId, attemptId, weight, count)
        } else {
            DummyDiaryEntryRepositoryImpl.newInstance().addAttempt(trainingId, exerciseId, weight, count)
        }
    }
}