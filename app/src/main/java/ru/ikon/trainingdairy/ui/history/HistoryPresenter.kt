package ru.ikon.trainingdairy.ui.history

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class HistoryPresenter : HistoryContract.Presenter {
    private var view: HistoryContract.View? = null
    private val repository = DummyDiaryEntryRepositoryImpl.newInstance()

    override fun attach(view: HistoryContract.View) {
        this.view = view
    }

    override fun onCreate(name: String) {
        val repository = DummyDiaryEntryRepositoryImpl()

        val history = repository.getHistory(name)

        view?.showExercises(history)
    }

    override fun detach() {
        this.view = null
    }
}