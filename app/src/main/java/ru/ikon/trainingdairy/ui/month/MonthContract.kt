package ru.ikon.trainingdairy.ui.month

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel

/**
 * Контракт взаимодействия Presenterа и View.
 */
class MonthContract {
    interface View {
        fun showData(data: List<DiaryEntryModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
    }
}