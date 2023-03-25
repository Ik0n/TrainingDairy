package ru.ikon.trainingdairy.ui.training.recycler

import ru.ikon.trainingdairy.domain.model.ExerciseModel

interface OnHistoryButtonClickListener {
    fun onHistoryButtonClick(data: ExerciseModel)
}