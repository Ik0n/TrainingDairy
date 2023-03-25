package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*
import kotlin.collections.ArrayList

class MeasurePresenter : MeasureContract.Presenter {

    private var view: MeasureContract.View? = null
    private val repository = DummyDiaryEntryRepositoryImpl.newInstance()

    override fun attach(view: MeasureContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            val parametersList = repository.getParameters(id)
            view?.showParameters(parametersList)
        }
    }

    override fun detach() {
        this.view = null
    }

    override fun onParameterDeleted(parameterId: Long, measureId: Long) {
        repository.deleteParameter(parameterId, measureId)
        val parametersList = repository.getParameters(measureId)
        view?.showParameters(parametersList)
    }

    override fun getParameters(measureId: Long): List<ParameterModel> {
        return if(measureId.toInt() != 0) {
            repository.getParameters(measureId)
        } else {
            ArrayList()
        }
    }

    override fun saveMeasure(date: Date) : Long {
        return repository.addMeasure(date)
    }
}