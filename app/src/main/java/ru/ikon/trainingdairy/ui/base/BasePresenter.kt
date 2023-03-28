package ru.ikon.trainingdairy.ui.base

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository

abstract class BasePresenter<V>(protected val repository : DiaryEntryRepository) {
    protected var view: V? = null

    fun attach(view: V) {
        this.view = view
    }

    fun detach() {
        view = null
    }
}