package ru.ikon.trainingdairy.ui.parameters

import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.base.BasePresenter

class ParametersPresenter(repository: DiaryEntryRepository) : ParametersContract.Presenter, BasePresenter<ParametersContract.View>(
    repository
) {

    override fun onCreate(id: Long) {
        if (id.toInt() != 0) {
            view?.showData(DummyDiaryEntryRepositoryImpl.newInstance().getParameters(id))
        }
    }

    override fun saveParametersList(id: Long, list: List<ParameterModel>) {
        DummyDiaryEntryRepositoryImpl.newInstance().addParameters(id, list)
    }
}