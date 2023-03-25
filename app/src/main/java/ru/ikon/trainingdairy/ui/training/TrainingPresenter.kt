package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class TrainingPresenter : TrainingContract.Presenter {

    private var view: TrainingContract.View? = null
    private val repository = DummyDiaryEntryRepositoryImpl.newInstance()

    override fun attach(view: TrainingContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long, date: Date) {
        // Создаём репозиторий с тестовыми данными. Позднее здесь будет загрузка данных
        // из базы, а пока - загрузка из тестового репозитория
        val repository = DummyDiaryEntryRepositoryImpl()

        val trainingModel =
            if (id != 0L) {
                // Получаем тренировку с указанным ID
                repository.getTraining(id)
            } else {
                // Если ID равен null (то есть, это новая тренировка), просто возвращаем новую тренировку
                TrainingModel(0, date)
            }

        // Передаём эти данные во View и просим отобразить их в виде списка
        view?.showData(trainingModel)
    }

    override fun getTraining(trainingId: Long): TrainingModel {
        return repository.getTraining(trainingId)
    }

    override fun detach() {
        this.view = null
    }

    override fun saveTraining(name: String, date: Date, comment: String) : Long {
        return repository.addTraining(name, date, comment)
    }

    override fun updateTraining(id: Long, name: String, date: Date, comment: String) {
        repository.updateTraining(id, name, date, comment)
    }

    override fun onExerciseDeleted(exerciseId: Long, trainingId: Long) {
        repository.deleteExercise(exerciseId, trainingId)
        val exerciseList = repository.getExercises(trainingId)
        view?.showExercises(exerciseList)
    }
}