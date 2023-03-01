package ru.ikon.trainingdairy.ui.day

class DayPresenter : DayContract.Presenter {

    private var  view: DayContract.View? = null

    override fun attach(view: DayContract.View) {
        this.view = view
    }

    override fun onCreate() {

    }

    override fun detach() {
        this.view = null
    }


}