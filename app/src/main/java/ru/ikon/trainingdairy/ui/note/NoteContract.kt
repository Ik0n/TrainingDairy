package ru.ikon.trainingdairy.ui.note

import ru.ikon.trainingdairy.domain.model.NoteModel
import java.util.*

class NoteContract {
    interface View {
        fun showData(note: NoteModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long, date: Date)
        fun detach()
        fun saveNote(date: Date, text: String)
        fun getNote(id: Long): NoteModel
        fun updateNote(id: Long, date: Date, text: String)
    }
}