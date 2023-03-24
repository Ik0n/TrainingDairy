package ru.ikon.trainingdairy.ui.attempt

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class AttemptPresenter : AttemptContract.Presenter {

    var view: AttemptContract.View? = null

    override fun attach(view: AttemptContract.View) {
        this.view = view
    }

    override fun onCreate(trainingId: Long, exerciseId: Long, attemptId: Long) {
        if (attemptId != 0L) {
            view?.showData(DummyDiaryEntryRepositoryImpl.newInstance().getAttempt(trainingId, exerciseId, attemptId))
        }
    }

    override fun detach() {
        this.view = null
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