package ru.ikon.trainingdairy.ui.training

import ru.ikon.trainingdairy.domain.model.TrainingModel

class TrainingContract {
    interface View {
        fun showData(data: TrainingModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long)
        fun detach()
    }
}