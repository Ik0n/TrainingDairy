package ru.ikon.trainingdairy.ui.exerciseattempts.recycler

import ru.ikon.trainingdairy.domain.model.AttemptModel

interface OnItemClickListener {
    fun onItemClick(attempt: AttemptModel)
}