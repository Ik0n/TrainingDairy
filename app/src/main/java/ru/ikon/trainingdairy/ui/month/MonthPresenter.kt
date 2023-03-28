package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter

class MonthPresenter(repository: DiaryEntryRepository) : MonthContract.Presenter,
    BasePresenter<MonthContract.View>(repository) {

    override fun onCreate() {
        val entriesList = repository.getEntries()

        // Передаём эти данные во View и просим отобразить их в календаре
        view?.showData(entriesList)
    }
}