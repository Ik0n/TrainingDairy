package ru.ikon.trainingdairy.ui.programslist

class ProgramsListContract {
    interface View {
        fun showData(data: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
    }
}