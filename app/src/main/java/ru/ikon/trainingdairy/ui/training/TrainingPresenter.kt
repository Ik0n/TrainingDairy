package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter
import java.util.*

class TrainingPresenter(repository: DiaryEntryRepository) : TrainingContract.Presenter, BasePresenter<TrainingContract.View>(
    repository
) {
    override fun onCreate(id: Long, date: Date) {
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

    override fun onSaveTraining(trainingId: Long, name: String, date: Date, comment: String) : Long {
        val id: Long
        if (trainingId == 0L) {
            id = repository.addTraining(name, date, comment)
        } else {
            id = trainingId
            repository.updateTraining(trainingId, name, date, comment)
        }
        return id
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