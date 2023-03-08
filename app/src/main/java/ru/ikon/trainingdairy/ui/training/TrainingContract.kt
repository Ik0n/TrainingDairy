package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import java.util.*

class TrainingContract {
    interface View {
        fun showData(data: List<DiaryEntryModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long)
        fun detach()
    }
}