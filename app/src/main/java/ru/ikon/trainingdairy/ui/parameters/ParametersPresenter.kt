package ru.ikon.trainingdairy.ui.parameters

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class ParametersPresenter : ParametersContract.Presenter {

    private var view: ParametersContract.View? = null

    override fun attach(view: ParametersContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            view?.showData(DummyDiaryEntryRepositoryImpl.newInstance().getParameters(id))
        }
    }

    override fun detach() {
        this.view = null
    }

    override fun saveParametersList(id: Long, list: List<ParameterModel>) {
        DummyDiaryEntryRepositoryImpl.newInstance().addParameters(id, list)
    }
}