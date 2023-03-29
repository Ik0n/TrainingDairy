package ru.ikon.trainingdairy.ui.note

import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter
import java.util.*

class NotePresenter(repository: DiaryEntryRepository) : NoteContract.Presenter, BasePresenter<NoteContract.View>(
    repository
) {

    override fun onCreate(id: Long, date: Date) {
        val noteModel =
            if (id != 0L) {
                // Получаем тренировку с указанным ID
                repository.getNote(id)
            } else {
                // Если ID равен null (то есть, это новая тренировка), просто возвращаем новую тренировку
                NoteModel(0, date)
            }

        // Передаём эти данные во View и просим отобразить их в виде списка
        view?.showData(noteModel)
    }

    override fun saveNote(date: Date, text: String) {
        repository.addNote(date, text)
    }

    override fun getNote(id: Long): NoteModel {
        return repository.getNote(id)
    }

    override fun updateNote(id: Long, date: Date, text: String) {
        repository.updateNote(id, date, text)
    }
}