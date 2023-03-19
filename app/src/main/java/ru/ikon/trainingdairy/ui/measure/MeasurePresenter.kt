package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*
import kotlin.collections.ArrayList

class MeasurePresenter : MeasureContract.Presenter {

    private var view: MeasureContract.View? = null

    override fun attach(view: MeasureContract.View) {
        this.view = view
    }

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            val repository = DummyDiaryEntryRepositoryImpl.newInstance()
            val parametersList = repository.getParameters(id)
            view?.showData(parametersList)
        }
    }

    override fun detach() {
        this.view = null
    }

    override fun deleteParameter(parameterId: Long, measureId: Long) {
        DummyDiaryEntryRepositoryImpl.newInstance().deleteParameter(parameterId, measureId)
    }

    override fun getParameters(measureId: Long): List<ParameterModel> {
        return if(measureId.toInt() != 0) {
            DummyDiaryEntryRepositoryImpl.newInstance().getParameters(measureId)
        } else {
            ArrayList()
        }
    }

    override fun saveMeasure(date: Date) : Long {
        return DummyDiaryEntryRepositoryImpl.newInstance().addMeasure(date)
    }
}