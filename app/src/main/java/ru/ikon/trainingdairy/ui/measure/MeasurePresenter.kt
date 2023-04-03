package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter
import java.util.*
import kotlin.collections.ArrayList

class MeasurePresenter(repository: DiaryEntryRepository) :
    MeasureContract.Presenter, BasePresenter<MeasureContract.View>(
    repository
) {

    override fun onCreate(id: Long, date: Date) {
        val measure: MeasureModel =
            if (id == 0L) {
                MeasureModel(id, date)
            } else {
                repository.getMeasure(id)   // TODO: Сделать так, чтобы измерение отсюда возвращалось уже со списком параметров
            }
        view?.showData(measure)
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

    override fun onSaveMeasure(date: Date, measureId: Long, comment: String): Long {
        val id: Long
        if (measureId == 0L) {
            id = repository.addMeasure(date, comment)
        } else {
            id = measureId
            repository.updateMeasure(measureId, date, comment)
        }
        return id
    }

    override fun getMeasure(measureId: Long): MeasureModel {
        return repository.getMeasure(measureId)
    }

    override fun updateMeasure(measureId: Long, date: Date, comment: String) {
        repository.updateMeasure(measureId, date, comment)
    }
}