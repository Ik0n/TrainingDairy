package ru.ikon.trainingdairy.ui.programslist

import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter

class ProgramsListPresenter(repository: DiaryEntryRepository) : ProgramsListContract.Presenter, BasePresenter<ProgramsListContract.View>(
    repository
) {
    override fun onCreate() {
        view?.showData("")
    }
}