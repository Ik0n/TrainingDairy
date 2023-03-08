package ru.ikon.trainingdairy.ui.note

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class NotePresenter : NoteContract.Presenter {

    private var view: NoteContract.View? = null

    override fun attach(view: NoteContract.View) {
        this.view = view
    }

    override fun onCreate(date: Date) {
        TODO("Not yet implemented")
    }



    override fun saveNote(date: Date, text: String) {
        DummyDiaryEntryRepositoryImpl.newInstance().addNote(
                NoteModel(
                    0, date, text
                )
            )
    }

    override fun getNote(id: Long): NoteModel {
        return DummyDiaryEntryRepositoryImpl.newInstance().getNote(id)
    }

    override fun detach() {
        this.view = null
    }
}