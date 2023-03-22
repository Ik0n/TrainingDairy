package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class TrainingPresenter : TrainingContract.Presenter {

    private var view: TrainingContract.View? = null

    override fun attach(view: TrainingContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long, date: Date) {
        // Создаём репозиторий с тестовыми данными. Позднее здесь будет загрузка данных
        // из базы, а пока - загрузка из тестового репозитория
        val repository = DummyDiaryEntryRepositoryImpl()

        val trainingModel =
            if (id > 0) {
                // Получаем тренировку с указанным ID
                repository.getTraining(id)
            } else {
                // Если ID равен null (то есть, это новая тренировка), просто возвращаем новую тренировку
                TrainingModel(date)
            }

        // Передаём эти данные во View и просим отобразить их в виде списка
        view?.showData(trainingModel)
    }

    override fun getTraining(trainingId: Long): TrainingModel {
        TODO("Not yet implemented")
    }

    override fun detach() {
        this.view = null
    }
}