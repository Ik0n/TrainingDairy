package ru.ikon.trainingdairy.ui.exercise

import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.base.BasePresenter
import kotlin.random.Random

class ExercisePresenter(repository: DiaryEntryRepository) : ExerciseContract.Presenter, BasePresenter<ExerciseContract.View>(
    repository
) {

    override fun onCreate(trainingId: Long) {

        val checkedExercises = DummyDiaryEntryRepositoryImpl.newInstance().getExercises(trainingId)

        val exerciseList = ArrayList<ExerciseModel>().apply {
            add(ExerciseModel("Выпады с гантелями"))
            add(ExerciseModel("Жим гантелей лёжа на наклонной скамье"))
            add(ExerciseModel("Жим гантелей сидя"))
            add(ExerciseModel("Жим ногами"))
            add(ExerciseModel("Жим от груди в тренажёре сидя"))
            add(ExerciseModel("Жим штанги лёжа"))
            add(ExerciseModel("Подъём гантелей на бицепс"))
            add(ExerciseModel("Подъём штанги на бицепс стоя"))
            add(ExerciseModel("Приседания со штангой на плечах"))
            add(ExerciseModel("Разведение гантелей в стороны"))
            add(ExerciseModel("Разведение гантелей стоя"))
            add(ExerciseModel("Разведение рук на тренажёре"))
            add(ExerciseModel("Разгибание ног на тренажёре"))
            add(ExerciseModel("Разгибание рук на верхнем блоке"))
            add(ExerciseModel("Разгибание руки с гантелью"))
            add(ExerciseModel("Сведение рук на тренажере"))
            add(ExerciseModel("Становая тяга"))
            add(ExerciseModel("Тяга горизонтального блока"))
            add(ExerciseModel("Тяга штанги в наклоне"))
            add(ExerciseModel("Французский жим лёжа"))
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