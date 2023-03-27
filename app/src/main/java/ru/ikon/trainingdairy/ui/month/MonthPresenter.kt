package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.base.BasePresenter

class MonthPresenter(repository: DiaryEntryRepository) : MonthContract.Presenter,
    BasePresenter<MonthContract.View>(repository) {

    override fun onCreate() {
        // Создаём репозиторий с тестовыми данными. Позднее здесь будет загрузка данных
        // из базы, а пока - загрузка из тестового репозитория
        val repository = DummyDiaryEntryRepositoryImpl()
        val entriesList = repository.getEntries()

        // Передаём эти данные во View и просим отобразить их в календаре
        view?.showData(entriesList)
    }
}