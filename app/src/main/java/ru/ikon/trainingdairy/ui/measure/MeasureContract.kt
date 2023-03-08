package ru.ikon.trainingdairy.ui.measure

import java.util.*

class MeasureContract {
    interface View {
        fun showData()
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(date: Date)
        fun detach()
    }
}