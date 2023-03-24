package ru.ikon.trainingdairy.ui.exercise

import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class ExercisePresenter : ExerciseContract.Presenter {

    private var view: ExerciseContract.View? = null


    override fun attach(view: ExerciseContract.View) {
        this.view = view
    }

    override fun onCreate(trainingId: Long) {

        val checkedExercises = DummyDiaryEntryRepositoryImpl.newInstance().getExercises(trainingId)


        val exerciseList = ArrayList<ExerciseModel>().apply {
            add(ExerciseModel("Выпады с гантелями"))
            add(ExerciseModel("Жим гантелей сидя"))
            add(ExerciseModel("Жим ногами"))
            add(ExerciseModel("Разведение гантелей стоя"))
        }

        exerciseList.forEach { exercise ->
            checkedExercises.forEach { checkedExercise ->
                if (exercise.name.equals(checkedExercise.name)) {
                    exercise.isChecked = checkedExercise.isChecked
                }
            }
        }


        view?.showData(exerciseList)
    }

    override fun detach() {
        this.view = null
    }

    override fun saveExercises(trainingId: Long, exerciseList: ArrayList<ExerciseModel>) {

        val list = ArrayList<ExerciseModel>()

        exerciseList.forEach {
            if (it.isChecked) {
                list.apply {
                    add(
                        ExerciseModel(it.name).apply {
                        this.id = Random.nextLong()
                            this.isChecked = it.isChecked
                            this.trainingId = trainingId
                        }
                    )
                }
            }
        }

        DummyDiaryEntryRepositoryImpl.newInstance().addExercises(trainingId, list)
    }

}