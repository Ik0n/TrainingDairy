package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel

/**
 * Контракт взаимодействия Presenterа и View.
 */
class MonthContract {
    interface View {
        fun showData(data: ArrayList<DiaryEntryModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
    }
}