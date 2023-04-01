package ru.ikon.trainingdairy.ui.measure

import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.ParameterModel
import java.util.*

class MeasureContract {
    interface View {
        fun showParameters(parametersList: List<ParameterModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long)
        fun detach()
        fun onParameterDeleted(parameterId: Long, measureId: Long)
        fun getParameters(measureId: Long): List<ParameterModel>
        fun getMeasure(measureId: Long): MeasureModel
        fun saveMeasure(date: Date, comment: String) : Long
        fun updateMeasure(measureId: Long, date: Date, comment: String)
    }
}