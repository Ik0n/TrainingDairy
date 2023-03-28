package ru.ikon.trainingdairy.ui.history

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter

class HistoryPresenter(repository: DiaryEntryRepository) : HistoryContract.Presenter, BasePresenter<HistoryContract.View>(
    repository
) {
    override fun onCreate(name: String) {
        val history = repository.getHistory(name)

        view?.showExercises(history)
    }
}