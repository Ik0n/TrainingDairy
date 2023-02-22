package ru.ikon.trainingdairy.ui.month

/**
 * Контракт взаимодействия Presenterа и View.
 */
class MonthContract {
    interface View {
        fun showData(data: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
    }
}