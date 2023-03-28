package ru.ikon.trainingdairy.ui.exercise.recycler

import ru.ikon.trainingdairy.domain.model.ExerciseModel

interface OnHistoryButtonClickListener {
    fun onHistoryButtonClick(data: ExerciseModel)
}