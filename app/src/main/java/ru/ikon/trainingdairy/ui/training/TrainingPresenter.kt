package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class TrainingPresenter : TrainingContract.Presenter {

    private var view: TrainingContract.View? = null

    override fun attach(view: TrainingContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long) {
        // Создаём репозиторий с тестовыми данными. Позднее здесь будет загрузка данных
        // из базы, а пока - загрузка из тестового репозитория
        val repository = DummyDiaryEntryRepositoryImpl()

        if (id > 0) {
            // Получаем тренировку с указанным ID
            val trainingModel = repository.getTraining(id)
        }
        // Передаём эти данные во View и просим отобразить их в виде списка
        //view?.showData(entriesList)
    }

    override fun detach() {
        this.view = null
    }
}