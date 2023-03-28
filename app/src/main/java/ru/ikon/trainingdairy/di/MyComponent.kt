package ru.ikon.trainingdairy.di

import dagger.Component
import ru.ikon.trainingdairy.ui.attempt.AttemptFragment
import ru.ikon.trainingdairy.ui.day.DayFragment
import ru.ikon.trainingdairy.ui.exercise.ExerciseFragment
import ru.ikon.trainingdairy.ui.exerciseattempts.ExerciseAttemptsFragment
import ru.ikon.trainingdairy.ui.history.HistoryFragment
import ru.ikon.trainingdairy.ui.measure.MeasureFragment
import ru.ikon.trainingdairy.ui.month.MonthFragment
import ru.ikon.trainingdairy.ui.note.NoteDialogFragment
import ru.ikon.trainingdairy.ui.parameters.ParametersFragment
import ru.ikon.trainingdairy.ui.programslist.ProgramsListFragment
import ru.ikon.trainingdairy.ui.training.TrainingFragment
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [DbModule::class])
interface MyComponent {
    fun inject(attemptFragment: AttemptFragment)
    fun inject(dayFragment: DayFragment)
    fun inject(exerciseFragment: ExerciseFragment)
    fun inject(exerciseAttemptsFragment: ExerciseAttemptsFragment)
    fun inject(historyFragment: HistoryFragment)
    fun inject(measureFragment: MeasureFragment)
    fun inject(monthFragment: MonthFragment)
    fun inject(noteDialogFragment: NoteDialogFragment)
    fun inject(parametersFragment: ParametersFragment)
    fun inject(programsListFragment: ProgramsListFragment)
    fun inject(trainingFragment: TrainingFragment)
    fun inject(userParametersFragment: UserParametersFragment)
}