package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.ParameterModel
import java.util.*

class MeasureContract {
    interface View {
        fun showData(data: MeasureModel)
        fun showParameters(parametersList: List<ParameterModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long, date: Date)
        fun detach()
        fun onParameterDeleted(parameterId: Long, measureId: Long)
        fun getParameters(measureId: Long): List<ParameterModel>
        fun onSaveMeasure(date: Date, measureId: Long, comment: String) : Long
        fun getMeasure(measureId: Long): MeasureModel
        fun updateMeasure(measureId: Long, date: Date, comment: String)
    }
}