package ru.ikon.trainingdairy.ui.programslist

class ProgramsListPresenter : ProgramsListContract.Presenter {

    private var view: ProgramsListContract.View? = null

    override fun attach(view: ProgramsListContract.View) {
        this.view = view
    }

    override fun onCreate() {
        view?.showData("ProgramsList fragment presenter")
    }

    override fun detach() {
        view = null
    }
}