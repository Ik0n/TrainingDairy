package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter
import java.util.*
import kotlin.collections.ArrayList

class MeasurePresenter(repository: DiaryEntryRepository) : MeasureContract.Presenter, BasePresenter<MeasureContract.View>(
    repository
) {

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            val parametersList = repository.getParameters(id)
            view?.showParameters(parametersList)
        }
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