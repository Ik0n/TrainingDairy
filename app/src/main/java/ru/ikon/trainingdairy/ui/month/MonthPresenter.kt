package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class MonthPresenter : MonthContract.Presenter {
    private var view: MonthContract.View? = null

    override fun attach(view: MonthContract.View) {
        this.view = view
    }

    override fun onCreate() {
        // Создаём список с тестовыми данными. Позднее здесь будет загрузка данных
        // из базы, а пока - загрузка из тестового репозитория
        val repository = DummyDiaryEntryRepositoryImpl()
        val entriesList = repository.getEntries()

        // Передаём эти данные во View и просим отобразить их в календаре
        view?.showData(entriesList)
    }

    override fun detach() {
        view = null
    }
}