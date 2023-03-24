package ru.ikon.trainingdairy.ui.training.recycler

import ru.ikon.trainingdairy.domain.model.ExerciseModel

interface OnItemClickListener {
    fun onItemClick(item: ExerciseModel)

}