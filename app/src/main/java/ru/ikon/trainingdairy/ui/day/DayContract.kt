package ru.ikon.trainingdairy.ui.day

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel

class DayContract {
    interface View {
        fun showData(data: ArrayList<DiaryEntryModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
    }
}