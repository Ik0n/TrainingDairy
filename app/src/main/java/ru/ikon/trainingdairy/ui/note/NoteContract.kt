package ru.ikon.trainingdairy.ui.note

import java.util.*

class NoteContract {
    interface View {
        fun showData()
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(date: Date)
        fun detach()
        fun saveNote(date: Date, text: String)
    }
}