package ru.ikon.trainingdairy.ui.day.recycler

import ru.ikon.trainingdairy.domain.model.DiaryEntryModel

interface OnDeleteButtonClickListener {
    fun onDeleteButtonClick(data: DiaryEntryModel)
}