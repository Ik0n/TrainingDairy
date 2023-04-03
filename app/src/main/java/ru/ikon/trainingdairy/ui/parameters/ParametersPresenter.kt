package ru.ikon.trainingdairy.ui.parameters

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter

class ParametersPresenter(repository: DiaryEntryRepository) : ParametersContract.Presenter, BasePresenter<ParametersContract.View>(
    repository
) {

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            view?.showData(repository.getParameters(id))
        }
    }

    override fun onSaveParametersList(id: Long, list: List<ParameterModel>) {
        repository.updateParameters(id, list)
    }
}