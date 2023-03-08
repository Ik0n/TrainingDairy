package ru.ikon.trainingdairy.ui.measure

import java.util.*

class MeasurePresenter : MeasureContract.Presenter {

    private var view: MeasureContract.View? = null

    override fun attach(view: MeasureContract.View) {
        this.view = view
    }

    override fun onCreate(date: Date) {
    }

    override fun detach() {
        this.view = null
    }
}