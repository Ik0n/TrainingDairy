package ru.ikon.trainingdairy.ui.parameters

import ru.ikon.trainingdairy.domain.model.ParameterModel
import java.util.*

class ParametersContract {
    interface View {
        fun showData(data: List<ParameterModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate(id: Long)
        fun detach()
        fun saveParametersList(id: Long,list: List<ParameterModel>)
    }
}