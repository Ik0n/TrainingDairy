package ru.ikon.trainingdairy.ui.day

import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*

class DayPresenter : DayContract.Presenter {

    private var view: DayContract.View? = null
    private val repository = DummyDiaryEntryRepositoryImpl.newInstance()
    private lateinit var date: Date

    override fun attach(view: DayContract.View) {
        this.view = view
    }

    override fun onCreate(date: Date) {
        this.date = date

        // Получаем из репозитория все записи за указанную дату
        val entriesList = repository.getEntries(date)

        // Передаём эти данные во View и просим отобразить их в виде списка
        view?.showData(entriesList)
    }

    override fun detach() {
        this.view = null
    }

    override fun onTrainingDeleted(trainingId: Long) {
        repository.deleteTraining(trainingId)
        val entriesList = repository.getEntries(date)
        view?.showData(entriesList)
    }

    override fun onMeasureDeleted(measureId: Long) {
        repository.deleteMeasure(measureId)
        val entriesList = repository.getEntries(date)
        view?.showData(entriesList)
    }

    override fun onNoteDeleted(noteId: Long) {
        repository.deleteNote(noteId)
        val entriesList = repository.getEntries(date)
        view?.showData(entriesList)
    }
}