package ru.ikon.trainingdairy.ui.measure.recycler

import ru.ikon.trainingdairy.domain.model.ParameterModel

interface OnDeleteButtonClickListener {
    fun onClick(data: ParameterModel)
}