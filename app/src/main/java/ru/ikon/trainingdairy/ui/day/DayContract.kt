package ru.ikon.trainingdairy.ui.day

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import java.util.*
import kotlin.collections.ArrayList

class DayContract {
    interface View {
        fun showData(data: List<DiaryEntryModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(date: Date)
        fun detach()
    }
}