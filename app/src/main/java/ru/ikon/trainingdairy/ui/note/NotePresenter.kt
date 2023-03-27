package ru.ikon.trainingdairy.ui.note

import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.base.BasePresenter
import java.util.*

class NotePresenter(repository: DiaryEntryRepository) : NoteContract.Presenter, BasePresenter<NoteContract.View>(
    repository
) {

    override fun onCreate(date: Date) {
        TODO("Not yet implemented")
    }

    override fun saveNote(date: Date, text: String) {
        DummyDiaryEntryRepositoryImpl.newInstance().addNote(date, text)
    }

    override fun getNote(id: Long): NoteModel {
        return DummyDiaryEntryRepositoryImpl.newInstance().getNote(id)
    }

    override fun updateNote(id: Long, date: Date, text: String) {
        DummyDiaryEntryRepositoryImpl.newInstance().updateNote(id, date, text)
    }
}